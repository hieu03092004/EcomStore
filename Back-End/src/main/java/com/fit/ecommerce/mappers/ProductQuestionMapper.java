package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fit.ecommerce.dtos.response.productQuestion.ProductQuestionResponse;
import com.fit.ecommerce.entities.ProductQuestion;

@Mapper(
        componentModel = "spring",
        uses = {ProductQuestionAnswerMapper.class}
)
public interface ProductQuestionMapper {

    @Mapping(source = "user.fullName", target = "userName")
    ProductQuestionResponse toResponse(ProductQuestion productQuestion);
}
