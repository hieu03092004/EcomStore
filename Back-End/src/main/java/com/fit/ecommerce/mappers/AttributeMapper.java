package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fit.ecommerce.dtos.response.attribute.AttributeResponse;
import com.fit.ecommerce.entities.Attribute;

@Mapper(componentModel = "spring")
public interface AttributeMapper {
    @Mapping(source = "category.name", target = "categoryName")
    AttributeResponse toResponse(Attribute attribute);

//    @Named("mapActiveAttributes")
//    default List<AttributeResponse> mapActiveAttributes(List<Attribute> attributes) {
//        if (attributes == null) return null;
//        return attributes.stream()
//                .filter(Attribute::isStatus) // chỉ lấy status = true
//                .map(this::toResponse)
//                .toList();
//    }
}