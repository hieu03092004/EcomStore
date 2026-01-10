package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fit.ecommerce.dtos.response.variant.VariantValueResponse;
import com.fit.ecommerce.entities.VariantValue;

@Mapper(componentModel = "spring")
public interface VariantValueMapper {
    @Mapping(source = "variant.id", target = "variantId")
    @Mapping(source = "variant.name", target = "variantName")
    VariantValueResponse toResponse(VariantValue value);
}