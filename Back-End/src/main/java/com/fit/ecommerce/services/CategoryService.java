package com.fit.ecommerce.services;

import jakarta.validation.Valid;

import java.util.List;

import com.fit.ecommerce.dtos.request.category.CategoryAddRequest;
import com.fit.ecommerce.dtos.response.base.PageResponse;
import com.fit.ecommerce.dtos.response.category.CategoryResponse;
import com.fit.ecommerce.entities.Category;

public interface CategoryService {

    CategoryResponse createCategory(CategoryAddRequest request);

    PageResponse<CategoryResponse> getCategories(int page, int size, String categoryName);

    CategoryResponse getCategoryById(Long id);

    CategoryResponse updateCategory(Long id, @Valid CategoryAddRequest request);

    void changeStatusCategory(Long id);

    Category getCategoryEntityById(Long id);
}
