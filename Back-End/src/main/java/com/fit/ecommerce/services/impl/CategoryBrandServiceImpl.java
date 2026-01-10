package com.fit.ecommerce.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fit.ecommerce.dtos.request.categoryBrand.SetBrandsForCategoryRequest;
import com.fit.ecommerce.dtos.response.brand.BrandResponse;
import com.fit.ecommerce.dtos.response.category.CategoryResponse;
import com.fit.ecommerce.entities.Brand;
import com.fit.ecommerce.entities.Category;
import com.fit.ecommerce.entities.CategoryBrand;
import com.fit.ecommerce.exceptions.ErrorCode;
import com.fit.ecommerce.exceptions.custom.ResourceNotFoundException;
import com.fit.ecommerce.mappers.BrandMapper;
import com.fit.ecommerce.mappers.CategoryMapper;
import com.fit.ecommerce.repositories.BrandRepository;
import com.fit.ecommerce.repositories.CategoryBrandRepository;
import com.fit.ecommerce.repositories.CategoryRepository;
import com.fit.ecommerce.services.CategoryBrandService;
import com.fit.ecommerce.services.CategoryService;

import java.util.List;
import java.util.stream.Collectors; // <-- THÊM

@Service
@RequiredArgsConstructor
public class CategoryBrandServiceImpl implements CategoryBrandService {

    private final CategoryBrandRepository categoryBrandRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public void setBrandsForCategory(SetBrandsForCategoryRequest request) {
        Category category = categoryService.getCategoryEntityById(request.getCategoryId());

        categoryBrandRepository.deleteAllByCategoryId(request.getCategoryId());

        // 3. Nếu danh sách ID mới rỗng hoặc null, thì dừng lại (chỉ xóa)
        if (request.getBrandIds() == null || request.getBrandIds().isEmpty()) {
            return;
        }

        // 4. Lấy tất cả entity Brand (tránh N+1 query)
        // Hàm findAllById sẽ chỉ trả về các Brand thực sự tồn tại
        List<Brand> brands = brandRepository.findAllById(request.getBrandIds());

        // 5. Tạo danh sách liên kết mới
        List<CategoryBrand> newAssignments = brands.stream()
                .map(brand -> CategoryBrand.builder()
                        .category(category)
                        .brand(brand)
                        .build())
                .collect(Collectors.toList());

        // 6. Lưu tất cả liên kết mới vào DB
        categoryBrandRepository.saveAll(newAssignments);
    }


    @Override
    public List<BrandResponse> getBrandsByCategoryId(
            Long categoryId, String brandName
    ) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException(ErrorCode.CATEGORY_NOT_FOUND);
        }


        List<Brand> brands = categoryBrandRepository.findBrandsByCategoryIdAndName(
                categoryId, brandName
        );

        return brands.stream().map(brandMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<CategoryResponse> getCategoriesByBrandId(
            Long brandId, String categoryName
    ) {
        if (!brandRepository.existsById(brandId)) {
            throw new ResourceNotFoundException(ErrorCode.BRAND_NOT_FOUND);
        }

        List<Category> categories = categoryBrandRepository.findCategoriesByBrandIdAndName(
                brandId, categoryName
        );

        return categories.stream().map(categoryMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<BrandResponse> getBrandsByCategorySlug(String slug) {
        List<Brand> brands = categoryBrandRepository.findBrandsByCategorySlug(slug);
        return brands.stream().map(brandMapper::toResponse).collect(Collectors.toList());
    }
}