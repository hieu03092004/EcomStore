package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;

import com.fit.ecommerce.entities.ProductImage;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
    default String toResponse(ProductImage productImage) {
        return productImage != null ? productImage.getUrl() : null;
    }
}