package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;

import com.fit.ecommerce.dtos.response.order.OrderResponse;
import com.fit.ecommerce.entities.Order;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, OrderDetailMapper.class})
public interface OrderMapper {

//    @Mapping(target = "orderDate", expression = "java(iuh.fit.ecommerce.utils.DateUtils.formatLocalDateTime(order.getOrderDate()))")
    OrderResponse toResponse(Order order);
}

