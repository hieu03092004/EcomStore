package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;

import com.fit.ecommerce.dtos.response.brand.BrandResponse;
import com.fit.ecommerce.entities.Brand;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandResponse toResponse(Brand brand);
}
