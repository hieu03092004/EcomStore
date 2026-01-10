package com.fit.ecommerce.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fit.ecommerce.dtos.request.deliveryAssignment.AssignShipperRequest;
import com.fit.ecommerce.dtos.request.deliveryAssignment.CompleteDeliveryRequest;
import com.fit.ecommerce.dtos.response.deliveryAssignment.DeliveryAssignmentResponse;
import com.fit.ecommerce.entities.DeliveryAssignment;
import com.fit.ecommerce.entities.DeliveryImage;
import com.fit.ecommerce.entities.Order;
import com.fit.ecommerce.entities.Staff;
import com.fit.ecommerce.enums.DeliveryStatus;
import com.fit.ecommerce.enums.OrderStatus;
import com.fit.ecommerce.exceptions.ErrorCode;
import com.fit.ecommerce.exceptions.custom.InvalidParamException;
import com.fit.ecommerce.exceptions.custom.ResourceNotFoundException;
import com.fit.ecommerce.exceptions.custom.UnauthorizedException;
import com.fit.ecommerce.mappers.DeliveryAssignmentMapper;
import com.fit.ecommerce.repositories.DeliveryAssignmentRepository;
import com.fit.ecommerce.repositories.DeliveryImageRepository;
import com.fit.ecommerce.repositories.OrderRepository;
import com.fit.ecommerce.services.DeliveryAssignmentService;
import com.fit.ecommerce.services.NotificationWebSocketService;
import com.fit.ecommerce.services.OrderService;
import com.fit.ecommerce.services.RankingService;
import com.fit.ecommerce.services.StaffService;
import com.fit.ecommerce.utils.SecurityUtils;

import static com.fit.ecommerce.enums.OrderStatus.SHIPPED;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryAssignmentServiceImpl implements DeliveryAssignmentService {
    private final DeliveryAssignmentRepository deliveryAssignmentRepository;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final StaffService staffService;
    private final DeliveryImageRepository deliveryImageRepository;
    private final SecurityUtils securityUtils;
    private final DeliveryAssignmentMapper deliveryAssignmentMapper;
    private final NotificationWebSocketService notificationWebSocketService;
    private final RankingService rankingService;

    @Override
    public void assignShipperToOrder(AssignShipperRequest request) {
        Staff leader = securityUtils.getCurrentStaff();

        if(leader.getLeader() == null || !leader.getLeader()) {
            throw new InvalidParamException(ErrorCode.DELIVERY_ONLY_TEAM_LEADER);
        }

        Order order = orderService.findById(request.getOrderId());

        if (!SHIPPED.equals(order.getStatus())) {
            throw new InvalidParamException(ErrorCode.DELIVERY_INVALID_STATUS);
        }

        Staff shipper = staffService.getStaffEntityById(request.getShipperId());

        boolean isDelivering = deliveryAssignmentRepository.existsByShipperAndDeliveryStatus(
                shipper, DeliveryStatus.DELIVERING);

        if (isDelivering) {
            throw new InvalidParamException(ErrorCode.DELIVERY_SHIPPER_BUSY);
        }

        Optional<DeliveryAssignment> existingAssignment = deliveryAssignmentRepository.findByOrder_Id(order.getId());

        if(existingAssignment.isPresent()) {
            throw new InvalidParamException(ErrorCode.DELIVERY_ALREADY_ASSIGNED);
        }

        DeliveryAssignment deliveryAssignment = DeliveryAssignment.builder()
                .order(order)
                .shipper(shipper)
                .deliveryStatus(DeliveryStatus.ASSIGNED)
                .expectedDeliveryDate(LocalDate.now().plusDays(3))
                .build();

        deliveryAssignment.setDeliveryStatus(DeliveryStatus.ASSIGNED);
        deliveryAssignmentRepository.save(deliveryAssignment);

        // Cập nhật status order thành ASSIGNED_SHIPPER
        order.setStatus(OrderStatus.ASSIGNED_SHIPPER);
        orderRepository.save(order);

        // Gửi WebSocket notification
        notificationWebSocketService.sendDeliveryNotification(
            deliveryAssignment,
            "ASSIGNED",
            String.format("Đơn hàng #%d đã được gán cho bạn", order.getId())
        );
    }

    @Override
    @Transactional
    public void startDelivery(Long deliveryAssignmentId) {
        DeliveryAssignment deliveryAssignment = findById(deliveryAssignmentId);
        Staff currentStaff = securityUtils.getCurrentStaff();

        if (!deliveryAssignment.getShipper().getId().equals(currentStaff.getId())) {
            throw new UnauthorizedException(ErrorCode.DELIVERY_NOT_ASSIGNED_TO_USER);
        }

        if (!DeliveryStatus.ASSIGNED.equals(deliveryAssignment.getDeliveryStatus())) {
            throw new InvalidParamException(ErrorCode.DELIVERY_INVALID_STATUS_TO_START);
        }

        boolean isCurrentlyDelivering = deliveryAssignmentRepository.existsByShipperAndDeliveryStatus(
                currentStaff, DeliveryStatus.DELIVERING);

        if (isCurrentlyDelivering) {
            throw new InvalidParamException(ErrorCode.DELIVERY_ANOTHER_ORDER_IN_PROGRESS);
        }

        deliveryAssignment.setDeliveryStatus(DeliveryStatus.DELIVERING);
        deliveryAssignmentRepository.save(deliveryAssignment);

        Order order = deliveryAssignment.getOrder();
        order.setStatus(OrderStatus.DELIVERING);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void completeDelivery(CompleteDeliveryRequest request) {
        DeliveryAssignment deliveryAssignment = findById(request.getDeliveryAssignmentId());

        Staff currentStaff = securityUtils.getCurrentStaff();

        if (!deliveryAssignment.getShipper().getId().equals(currentStaff.getId())) {
            throw new UnauthorizedException(ErrorCode.DELIVERY_NOT_ASSIGNED_TO_USER);
        }

        if (!DeliveryStatus.DELIVERING.equals(deliveryAssignment.getDeliveryStatus())) {
            throw new InvalidParamException(ErrorCode.DELIVERY_INVALID_STATUS_TO_COMPLETE);
        }

        if (Boolean.TRUE.equals(request.getSuccess())) {
            deliveryAssignment.setDeliveryStatus(DeliveryStatus.DELIVERED);
            deliveryAssignment.getOrder().setStatus(OrderStatus.COMPLETED);
            
            // Update customer ranking when delivery is completed successfully
            rankingService.updateCustomerRanking(deliveryAssignment.getOrder());
        } else {
            deliveryAssignment.setDeliveryStatus(DeliveryStatus.FAILED);
            deliveryAssignment.getOrder().setStatus(OrderStatus.FAILED);
        }

        deliveryAssignment.setNote(request.getNote());
        deliveryAssignment.setDeliveredAt(LocalDateTime.now());

        if (request.getImageUrls() != null && !request.getImageUrls().isEmpty()) {
            List<DeliveryImage> images = request.getImageUrls().stream()
                    .map(url -> DeliveryImage.builder()
                            .imageUrl(url)
                            .deliveryAssignment(deliveryAssignment)
                            .build())
                    .collect(Collectors.toList());
            deliveryImageRepository.saveAll(images);
        }

        deliveryAssignmentRepository.save(deliveryAssignment);
        orderRepository.save(deliveryAssignment.getOrder());
    }

    private DeliveryAssignment findById(Long id) {
        return deliveryAssignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.DELIVERY_NOT_FOUND));
    }

    @Override
    public DeliveryAssignmentResponse getDeliveryAssignmentById(Long id) {
        DeliveryAssignment deliveryAssignment = findById(id);
        return deliveryAssignmentMapper.toResponse(deliveryAssignment);
    }

    @Override
    public List<DeliveryAssignmentResponse> getMyDeliveries() {
        Staff currentStaff = securityUtils.getCurrentStaff();
        List<DeliveryAssignment> deliveries = deliveryAssignmentRepository.findByShipper(currentStaff);
        return deliveries.stream()
                .map(deliveryAssignmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DeliveryAssignmentResponse> getMyDeliveringOrders() {
        Staff currentStaff = securityUtils.getCurrentStaff();
        List<DeliveryAssignment> deliveringOrders = deliveryAssignmentRepository
                .findByShipperAndDeliveryStatus(currentStaff, DeliveryStatus.DELIVERING);
        return deliveringOrders.stream()
                .map(deliveryAssignmentMapper::toResponse)
                .collect(Collectors.toList());
    }
}