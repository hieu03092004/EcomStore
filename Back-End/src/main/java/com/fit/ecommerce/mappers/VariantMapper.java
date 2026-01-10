package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fit.ecommerce.dtos.response.variant.VariantResponse;
import com.fit.ecommerce.entities.Variant;

@Mapper(componentModel = "spring", uses = {VariantValueMapper.class, CategoryMapper.class})
public interface VariantMapper {

    @Mapping(source = "variantValues", target = "variantValues")
    VariantResponse toResponse(Variant variant);
}