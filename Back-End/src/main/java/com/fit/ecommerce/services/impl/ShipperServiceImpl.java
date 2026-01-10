package com.fit.ecommerce.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.fit.ecommerce.dtos.response.shipper.ShipperResponse;
import com.fit.ecommerce.entities.Staff;
import com.fit.ecommerce.enums.DeliveryStatus;
import com.fit.ecommerce.mappers.StaffMapper;
import com.fit.ecommerce.repositories.DeliveryAssignmentRepository;
import com.fit.ecommerce.repositories.RoleRepository;
import com.fit.ecommerce.repositories.StaffRepository;
import com.fit.ecommerce.services.ShipperService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShipperServiceImpl implements ShipperService {

    private final StaffRepository staffRepository;
    private final DeliveryAssignmentRepository deliveryAssignmentRepository;
    private final StaffMapper staffMapper;

    @Override
    public List<ShipperResponse> getAllActiveShippers() {
        return staffRepository.findAll().stream()
                .filter(staff -> staff.getActive() && hasShipperRole(staff))
                .filter(this::isShipperAvailable)
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipperResponse> getAllShippers() {
        return staffRepository.findAll().stream()
                .filter(this::hasShipperRole)
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private boolean hasShipperRole(Staff staff) {
        return staff.getUserRoles().stream()
                .anyMatch(userRole -> "SHIPPER".equalsIgnoreCase(userRole.getRole().getName()));
    }

    private boolean isShipperAvailable(Staff staff) {
        return !deliveryAssignmentRepository.existsByShipperAndDeliveryStatus(
                staff, DeliveryStatus.DELIVERING);
    }

    private ShipperResponse mapToResponse(Staff staff) {
        return staffMapper.toShipperResponse(staff);
    }
}
