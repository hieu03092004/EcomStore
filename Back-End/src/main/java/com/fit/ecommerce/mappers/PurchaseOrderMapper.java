package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fit.ecommerce.dtos.response.purchaseOrder.PurchaseOrderResponse;
import com.fit.ecommerce.entities.PurchaseOrder;

@Mapper(componentModel = "spring", uses = {SupplierMapper.class, StaffMapper.class, PurchaseOrderDetailMapper.class})
public interface PurchaseOrderMapper {
    
    @Mapping(target = "purchaseDate", expression = "java(com.fit.ecommerce.utils.DateUtils.formatLocalDateTime(purchaseOrder.getPurchaseDate()))")
    @Mapping(target = "details", ignore = true)
    PurchaseOrderResponse toResponse(PurchaseOrder purchaseOrder);
}
