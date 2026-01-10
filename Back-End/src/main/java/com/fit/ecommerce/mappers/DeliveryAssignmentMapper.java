package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fit.ecommerce.dtos.response.deliveryAssignment.DeliveryAssignmentResponse;
import com.fit.ecommerce.entities.DeliveryAssignment;
import com.fit.ecommerce.entities.DeliveryImage;
import com.fit.ecommerce.entities.Staff;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderMapper.class, StaffMapper.class})
public interface DeliveryAssignmentMapper {

    @Mapping(target = "expectedDeliveryDate", expression = "java(com.fit.ecommerce.utils.DateUtils.formatDate(deliveryAssignment.getExpectedDeliveryDate()))")
    @Mapping(target = "deliveredAt", expression = "java(com.fit.ecommerce.utils.DateUtils.formatLocalDateTime(deliveryAssignment.getDeliveredAt()))")
    @Mapping(target = "createdAt", expression = "java(com.fit.ecommerce.utils.DateUtils.formatLocalDateTime(deliveryAssignment.getCreatedAt()))")
    @Mapping(target = "deliveryImages", expression = "java(mapDeliveryImages(deliveryAssignment))")
    DeliveryAssignmentResponse toResponse(DeliveryAssignment deliveryAssignment);

    default List<String> mapDeliveryImages(DeliveryAssignment deliveryAssignment) {
        if (deliveryAssignment.getDeliveryImages() == null) {
            return List.of();
        }
        return deliveryAssignment.getDeliveryImages().stream()
                .map(DeliveryImage::getImageUrl)
                .toList();
    }
}