package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fit.ecommerce.dtos.request.article.ArticleCategoryAddRequest;
import com.fit.ecommerce.dtos.response.article.ArticleCategoryResponse;
import com.fit.ecommerce.entities.ArticleCategory;

@Mapper(componentModel = "spring")
public interface ArticleCategoryMapper {

    ArticleCategoryResponse toResponse(ArticleCategory articleCategory);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    ArticleCategory toEntity(ArticleCategoryAddRequest request);
}