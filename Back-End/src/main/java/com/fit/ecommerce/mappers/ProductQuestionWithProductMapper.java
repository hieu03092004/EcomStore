package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fit.ecommerce.dtos.response.productQuestion.ProductQuestionWithProductResponse;
import com.fit.ecommerce.entities.ProductQuestion;

@Mapper(
        componentModel = "spring",
        uses = {ProductQuestionAnswerMapper.class}
)
public interface ProductQuestionWithProductMapper {
    @Mapping(source = "user.fullName", target = "userName")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.slug", target = "productSlug")
    @Mapping(source = "product.thumbnail", target = "productImage")
    ProductQuestionWithProductResponse toResponse(ProductQuestion productQuestion);
}
