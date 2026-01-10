package com.fit.ecommerce.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fit.ecommerce.dtos.request.purchaseOrder.PurchaseOrderDetailRequest;
import com.fit.ecommerce.dtos.request.purchaseOrder.PurchaseOrderRequest;
import com.fit.ecommerce.dtos.response.base.PageResponse;
import com.fit.ecommerce.dtos.response.purchaseOrder.PurchaseOrderResponse;
import com.fit.ecommerce.entities.*;
import com.fit.ecommerce.exceptions.ErrorCode;
import com.fit.ecommerce.exceptions.custom.InvalidParamException;
import com.fit.ecommerce.exceptions.custom.ResourceNotFoundException;
import com.fit.ecommerce.mappers.PurchaseOrderDetailMapper;
import com.fit.ecommerce.mappers.PurchaseOrderMapper;
import com.fit.ecommerce.repositories.*;
import com.fit.ecommerce.services.PurchaseOrderService;
import com.fit.ecommerce.specifications.PurchaseOrderSpecification;
import com.fit.ecommerce.utils.SecurityUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderDetailRepository purchaseOrderDetailRepository;
    private final SupplierRepository supplierRepository;
    private final ProductVariantRepository productVariantRepository;
    private final SecurityUtils securityUtils;
    private final PurchaseOrderMapper purchaseOrderMapper;
    private final PurchaseOrderDetailMapper purchaseOrderDetailMapper;

    @Override
    @Transactional
    public PurchaseOrderResponse createPurchaseOrder(PurchaseOrderRequest request) {
        // Get current staff
        Staff staff = securityUtils.getCurrentStaff();

        // Validate supplier
        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.SUPPLIER_NOT_FOUND));

        if (!supplier.getStatus()) {
            throw new InvalidParamException(ErrorCode.SUPPLIER_INACTIVE);
        }

        // Create purchase order
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setPurchaseDate(LocalDateTime.now());
        purchaseOrder.setSupplier(supplier);
        purchaseOrder.setStaff(staff);
        purchaseOrder.setNote(request.getNote());

        // Calculate total price and create details
        double totalPrice = 0.0;
        List<PurchaseOrderDetail> details = new ArrayList<>();

        for (PurchaseOrderDetailRequest detailRequest : request.getDetails()) {
            ProductVariant productVariant = productVariantRepository.findById(detailRequest.getProductVariantId())
                    .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));

            PurchaseOrderDetail detail = new PurchaseOrderDetail();
            detail.setPurchaseOrder(purchaseOrder);
            detail.setProductVariant(productVariant);
            detail.setQuantity(detailRequest.getQuantity());
            detail.setPrice(detailRequest.getPrice());

            totalPrice += detailRequest.getPrice() * detailRequest.getQuantity();
            details.add(detail);

            // Update product variant stock
            productVariant.setStock(productVariant.getStock() + detailRequest.getQuantity().intValue());
            productVariantRepository.save(productVariant);
        }

        purchaseOrder.setTotalPrice(totalPrice);

        // Save purchase order and details
        PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        purchaseOrderDetailRepository.saveAll(details);

        // Build response
        PurchaseOrderResponse response = purchaseOrderMapper.toResponse(savedPurchaseOrder);
        response.setDetails(details.stream()
                .map(purchaseOrderDetailMapper::toResponse)
                .toList());

        return response;
    }

    @Override
    public PageResponse<PurchaseOrderResponse> getAllPurchaseOrders(
            int page, 
            int size,
            String supplierId,
            String supplierName,
            LocalDate startDate,
            LocalDate endDate
    ) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "id"));

        Specification<PurchaseOrder> spec = (root, query, criteriaBuilder) -> 
            criteriaBuilder.conjunction();

        if (supplierId != null && !supplierId.isBlank()) {
            spec = spec.and(PurchaseOrderSpecification.hasSupplierId(supplierId));
        }

        if (supplierName != null && !supplierName.isBlank()) {
            spec = spec.and(PurchaseOrderSpecification.hasSupplierName(supplierName));
        }

        if (startDate != null) {
            spec = spec.and(PurchaseOrderSpecification.hasStartDate(startDate));
        }

        if (endDate != null) {
            spec = spec.and(PurchaseOrderSpecification.hasEndDate(endDate));
        }

        Page<PurchaseOrder> purchaseOrderPage = purchaseOrderRepository.findAll(spec, pageable);

        List<PurchaseOrderResponse> responses = purchaseOrderPage.getContent().stream()
                .map(po -> {
                    PurchaseOrderResponse response = purchaseOrderMapper.toResponse(po);
                    List<PurchaseOrderDetail> details = purchaseOrderDetailRepository.findByPurchaseOrderId(po.getId());
                    response.setDetails(details.stream()
                            .map(purchaseOrderDetailMapper::toResponse)
                            .toList());
                    return response;
                })
                .toList();

        return PageResponse.<PurchaseOrderResponse>builder()
                .data(responses)
                .totalPage(purchaseOrderPage.getTotalPages())
                .totalItem(purchaseOrderPage.getTotalElements())
                .page(page)
                .limit(size)
                .build();
    }

    @Override
    public PurchaseOrderResponse getPurchaseOrderById(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.PURCHASE_ORDER_NOT_FOUND));

        PurchaseOrderResponse response = purchaseOrderMapper.toResponse(purchaseOrder);
        
        List<PurchaseOrderDetail> details = purchaseOrderDetailRepository.findByPurchaseOrderId(id);
        response.setDetails(details.stream()
                .map(purchaseOrderDetailMapper::toResponse)
                .toList());

        return response;
    }
}
