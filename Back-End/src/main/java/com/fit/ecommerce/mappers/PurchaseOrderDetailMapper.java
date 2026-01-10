package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;

import com.fit.ecommerce.dtos.response.purchaseOrder.PurchaseOrderDetailResponse;
import com.fit.ecommerce.entities.PurchaseOrderDetail;

@Mapper(componentModel = "spring", uses = {ProductVariantOrderMapper.class})
public interface PurchaseOrderDetailMapper {
    PurchaseOrderDetailResponse toResponse(PurchaseOrderDetail purchaseOrderDetail);
}
