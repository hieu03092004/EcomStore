package com.fit.ecommerce.dtos.response.deliveryAssignment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fit.ecommerce.dtos.response.order.OrderResponse;
import com.fit.ecommerce.dtos.response.shipper.ShipperResponse;
import com.fit.ecommerce.enums.DeliveryStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAssignmentResponse {
    private Long id;
    private OrderResponse order;
    private ShipperResponse shipper;
    private String expectedDeliveryDate;
    private DeliveryStatus deliveryStatus;
    private String deliveredAt;
    private String createdAt;
    private String note;
    private List<String> deliveryImages;
}