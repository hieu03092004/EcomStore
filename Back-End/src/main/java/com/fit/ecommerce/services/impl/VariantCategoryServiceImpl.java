package com.fit.ecommerce.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fit.ecommerce.dtos.request.variantCategory.SetVariantsForCategoryRequest;
import com.fit.ecommerce.dtos.response.variantCategory.VariantCategoryResponse;
import com.fit.ecommerce.entities.Category;
import com.fit.ecommerce.entities.Variant;
import com.fit.ecommerce.entities.VariantCategory;
import com.fit.ecommerce.exceptions.ErrorCode;
import com.fit.ecommerce.exceptions.custom.ResourceNotFoundException;
import com.fit.ecommerce.mappers.VariantCategoryMapper;
import com.fit.ecommerce.repositories.CategoryRepository;
import com.fit.ecommerce.repositories.VariantCategoryRepository;
import com.fit.ecommerce.repositories.VariantRepository;
import com.fit.ecommerce.services.CategoryService;
import com.fit.ecommerce.services.VariantCategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class VariantCategoryServiceImpl implements VariantCategoryService {

    private final VariantCategoryRepository variantCategoryRepository;
    private final VariantRepository variantRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final VariantCategoryMapper variantCategoryMapper;

    @Override
    @Transactional
    public void setVariantsForCategory(SetVariantsForCategoryRequest request) {
        Category category = categoryService.getCategoryEntityById(request.getCategoryId());

        variantCategoryRepository.deleteAllByCategoryId(request.getCategoryId());

        if (request.getVariantIds() == null || request.getVariantIds().isEmpty()) {
            return;
        }

        List<Variant> variants = variantRepository.findAllById(request.getVariantIds());

        List<VariantCategory> newAssignments = variants.stream()
                .map(variant -> VariantCategory.builder()
                        .category(category)
                        .variant(variant)
                        .build())
                .collect(Collectors.toList());

        variantCategoryRepository.saveAll(newAssignments);
    }

    @Override
    public List<VariantCategoryResponse> getVariantCategoriesByCategoryId(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException(ErrorCode.CATEGORY_NOT_FOUND);
        }

        List<VariantCategory> variantCategories = variantCategoryRepository.findVariantCategoriesByCategoryId(categoryId);
        return variantCategories.stream()
                .map(variantCategoryMapper::toResponse)
                .collect(Collectors.toList());
    }
}

