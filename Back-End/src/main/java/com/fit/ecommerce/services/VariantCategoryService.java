package com.fit.ecommerce.services;

import java.util.List;

import com.fit.ecommerce.dtos.request.variantCategory.SetVariantsForCategoryRequest;
import com.fit.ecommerce.dtos.response.variantCategory.VariantCategoryResponse;

public interface VariantCategoryService {

    void setVariantsForCategory(SetVariantsForCategoryRequest request);

    List<VariantCategoryResponse> getVariantCategoriesByCategoryId(Long categoryId);
}

