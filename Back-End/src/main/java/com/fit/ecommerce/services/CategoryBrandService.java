package com.fit.ecommerce.services;

// import iuh.fit.ecommerce.dtos.response.categoryBrand.CategoryBrandResponse; // <-- XÓA
import java.util.List;

import com.fit.ecommerce.dtos.request.categoryBrand.SetBrandsForCategoryRequest;
import com.fit.ecommerce.dtos.response.brand.BrandResponse;
import com.fit.ecommerce.dtos.response.category.CategoryResponse;

public interface CategoryBrandService {

    void setBrandsForCategory(SetBrandsForCategoryRequest request);


    List<BrandResponse> getBrandsByCategoryId(
            Long categoryId, String brandName
    );

    List<CategoryResponse> getCategoriesByBrandId(
            Long brandId, String categoryName
    );

    List<BrandResponse> getBrandsByCategorySlug(String slug);
}