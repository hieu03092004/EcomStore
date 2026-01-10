package com.fit.ecommerce.dtos.request.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fit.ecommerce.enums.PaymentMethod;

@Getter
@Setter
public class StaffOrderCreationRequest {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Customer phone is required")
    private String customerPhone;

    private String customerEmail;

    private String note;

    private Long voucherId; // Optional: apply voucher for customer

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod; // CASH hoặc VN_PAY

    @NotEmpty(message = "Order items cannot be empty")
    private List<StaffOrderItem> items;
}

