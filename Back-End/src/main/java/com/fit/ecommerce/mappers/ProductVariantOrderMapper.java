package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fit.ecommerce.dtos.response.product.ProductVariantOrderResponse;
import com.fit.ecommerce.entities.ProductVariant;

@Mapper(componentModel = "spring")
public interface ProductVariantOrderMapper {

    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "productThumbnail", source = "product.thumbnail")
    @Mapping(target = "brandName", source = "product.brand.name")
    @Mapping(target = "categoryName", source = "product.category.name")
    ProductVariantOrderResponse toOrderResponse(ProductVariant productVariant);
}

