package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fit.ecommerce.dtos.response.productQuestion.ProductQuestionAnswerResponse;
import com.fit.ecommerce.dtos.response.productQuestion.ProductQuestionResponse;
import com.fit.ecommerce.entities.ProductQuestion;
import com.fit.ecommerce.entities.ProductQuestionAnswer;

@Mapper(componentModel = "spring")
public interface ProductQuestionAnswerMapper {

    @Mapping(source = "user.fullName", target = "userName")
    ProductQuestionAnswerResponse toResponse(ProductQuestionAnswer productQuestionAnswer);

}
