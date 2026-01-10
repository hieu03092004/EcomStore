package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;

import com.fit.ecommerce.dtos.response.product.ProductAttributeResponse;
import com.fit.ecommerce.entities.ProductAttributeValue;

@Mapper(componentModel = "spring", uses = AttributeMapper.class)
public interface ProductAttributeValueMapper {
    ProductAttributeResponse toResponse(ProductAttributeValue productAttributeValue);
}
