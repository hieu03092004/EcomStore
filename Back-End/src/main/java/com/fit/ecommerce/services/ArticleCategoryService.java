package com.fit.ecommerce.services;

import java.util.List;

import com.fit.ecommerce.dtos.request.article.ArticleCategoryAddRequest;
import com.fit.ecommerce.dtos.response.article.ArticleCategoryResponse;
import com.fit.ecommerce.dtos.response.base.PageResponse;

public interface ArticleCategoryService {

    ArticleCategoryResponse createCategory(ArticleCategoryAddRequest request);

    ArticleCategoryResponse getCategoryBySlug(String slug);

    ArticleCategoryResponse getCategoryById(Long id);

    ArticleCategoryResponse updateCategory(Long id, ArticleCategoryAddRequest request);

    void deleteCategory(Long id);

    PageResponse<ArticleCategoryResponse> getAllCategories(int page, int limit, String title);
}