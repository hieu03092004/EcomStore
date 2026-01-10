package com.fit.ecommerce.dtos.response.purchaseOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fit.ecommerce.dtos.response.staff.StaffResponse;
import com.fit.ecommerce.dtos.response.supplier.SupplierResponse;

@Getter
@Setter
@Builder
public class PurchaseOrderResponse {
    private Long id;
    private String purchaseDate;
    private Double totalPrice;
    private String note;
    private SupplierResponse supplier;
    private StaffResponse staff;
    private List<PurchaseOrderDetailResponse> details;
}
