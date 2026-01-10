package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fit.ecommerce.dtos.response.variantCategory.VariantCategoryResponse;
import com.fit.ecommerce.entities.VariantCategory;

@Mapper(componentModel = "spring")
public interface VariantCategoryMapper {

    /**
     * Chuyển đổi từ Entity VariantCategory sang VariantCategoryResponse DTO.
     */
    @Mapping(source = "variant.id", target = "variantId")
    @Mapping(source = "variant.name", target = "variantName")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    VariantCategoryResponse toResponse(VariantCategory entity);
}

