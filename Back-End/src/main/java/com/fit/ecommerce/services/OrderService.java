package com.fit.ecommerce.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import com.fit.ecommerce.dtos.request.order.OrderCreationRequest;
import com.fit.ecommerce.dtos.request.order.StaffOrderCreationRequest;
import com.fit.ecommerce.dtos.response.base.PageResponse;
import com.fit.ecommerce.dtos.response.order.OrderResponse;
import com.fit.ecommerce.entities.Order;
import com.fit.ecommerce.enums.OrderStatus;

import java.time.LocalDate;

public interface OrderService {
    Object customerCreateOrder(OrderCreationRequest orderCreationRequest, HttpServletRequest request);
    Object staffCreateOrder(StaffOrderCreationRequest request, HttpServletRequest httpRequest);
    PageResponse<OrderResponse> getMyOrders(int page, int size, List<String> status, String startDate, String endDate);

    Order findById(Long id);

    PageResponse<OrderResponse> getAllOrdersForAdmin(
            String customerName, 
            LocalDate orderDate, 
            String customerPhone, 
            OrderStatus status, 
            Boolean isPickup, 
            int page, 
            int size
    );

    OrderResponse getOrderDetailById(Long id);

    OrderResponse confirmOrder(Long orderId);

    OrderResponse cancelOrder(Long orderId);

    OrderResponse processOrder(Long orderId);

    OrderResponse completeOrder(Long orderId);

    PageResponse<OrderResponse> getOrdersNeedShipper(int page, int size);
}
