package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;

import com.fit.ecommerce.dtos.response.order.OrderDetailResponse;
import com.fit.ecommerce.entities.OrderDetail;

@Mapper(componentModel = "spring", uses = {ProductVariantOrderMapper.class})
public interface OrderDetailMapper {

    OrderDetailResponse toResponse(OrderDetail orderDetail);
}
