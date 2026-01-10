package com.fit.ecommerce.services;


import java.util.List;

import com.fit.ecommerce.dtos.request.deliveryAssignment.AssignShipperRequest;
import com.fit.ecommerce.dtos.request.deliveryAssignment.CompleteDeliveryRequest;
import com.fit.ecommerce.dtos.response.deliveryAssignment.DeliveryAssignmentResponse;

public interface DeliveryAssignmentService {
    void assignShipperToOrder(AssignShipperRequest assignShipperRequest);

    void startDelivery(Long deliveryAssignmentId);

    void completeDelivery(CompleteDeliveryRequest completeDeliveryRequest);

    DeliveryAssignmentResponse getDeliveryAssignmentById(Long id);

    List<DeliveryAssignmentResponse> getMyDeliveries();

    List<DeliveryAssignmentResponse> getMyDeliveringOrders();
}