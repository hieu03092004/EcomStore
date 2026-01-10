package com.fit.ecommerce.services;

import com.fit.ecommerce.dtos.response.NotificationResponse;
import com.fit.ecommerce.entities.DeliveryAssignment;
import com.fit.ecommerce.entities.Order;

public interface NotificationWebSocketService {
    void sendOrderNotification(Order order, String action, String message);
    void sendDeliveryNotification(DeliveryAssignment deliveryAssignment, String action, String message);
}

