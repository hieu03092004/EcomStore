package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.fit.ecommerce.dtos.response.category.CategoryResponse;
import com.fit.ecommerce.entities.Category;

@Mapper(componentModel = "spring", uses = {AttributeMapper.class})
public interface CategoryMapper {
//    @Mapping(target = "attributes", source = "attributes", qualifiedByName = "mapActiveAttributes")
    CategoryResponse toResponse(Category category);

}