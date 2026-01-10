package com.fit.ecommerce.services;

import java.time.LocalDate;
import java.util.List;

import com.fit.ecommerce.dtos.request.purchaseOrder.PurchaseOrderRequest;
import com.fit.ecommerce.dtos.response.base.PageResponse;
import com.fit.ecommerce.dtos.response.purchaseOrder.PurchaseOrderResponse;

public interface PurchaseOrderService {
    PurchaseOrderResponse createPurchaseOrder(PurchaseOrderRequest request);
    
    PageResponse<PurchaseOrderResponse> getAllPurchaseOrders(
            int page, 
            int size,
            String supplierId,
            String supplierName,
            LocalDate startDate,
            LocalDate endDate
    );
    
    PurchaseOrderResponse getPurchaseOrderById(Long id);
}



