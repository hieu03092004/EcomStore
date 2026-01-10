package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;

import com.fit.ecommerce.dtos.response.product.ProductVariantValueResponse;
import com.fit.ecommerce.entities.ProductVariantValue;

@Mapper(componentModel = "spring", uses = VariantValueMapper.class)
public interface ProductVariantValueMapper {
    ProductVariantValueResponse toResponse(ProductVariantValue entity);
}

