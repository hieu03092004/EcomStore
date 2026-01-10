package com.fit.ecommerce.dtos.response.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fit.ecommerce.dtos.response.customer.CustomerResponse;
import com.fit.ecommerce.entities.Customer;
import com.fit.ecommerce.entities.OrderDetail;
import com.fit.ecommerce.enums.OrderStatus;
import com.fit.ecommerce.enums.PaymentMethod;

@Getter
@Setter
@Builder
public class OrderResponse {
    private Long id;
    private String receiverAddress;
    private String receiverName;
    private String receiverPhone;
    private String orderDate;
    private OrderStatus status;
    private String note;
    private PaymentMethod paymentMethod;
    private Boolean isPickup;
    private Double totalPrice;
    private Double totalDiscount;
    private Double finalTotalPrice;
    private CustomerResponse customer;
    private List<OrderDetailResponse> orderDetails;
}

