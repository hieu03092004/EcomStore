package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.fit.ecommerce.dtos.request.article.ArticleAddRequest;
import com.fit.ecommerce.dtos.response.article.ArticleCategoryResponse;
import com.fit.ecommerce.dtos.response.article.ArticleResponse;
import com.fit.ecommerce.entities.Article;
import com.fit.ecommerce.entities.ArticleCategory;
import com.fit.ecommerce.entities.Staff;

@Mapper(componentModel = "spring", uses = ArticleCategoryMapper.class)
public interface ArticleMapper {


    @Mapping(source = "staff.fullName", target = "staffName")
    @Mapping(source = "articleCategory", target = "category")
    ArticleResponse toResponse(Article article);

    @Mapping(target = "staff", ignore = true)
    @Mapping(target = "articleCategory", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    Article toEntity(ArticleAddRequest request);
}