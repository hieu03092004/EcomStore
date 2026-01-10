package com.fit.ecommerce.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fit.ecommerce.dtos.request.variantCategory.SetVariantsForCategoryRequest;
import com.fit.ecommerce.dtos.response.base.ResponseSuccess;
import com.fit.ecommerce.dtos.response.variantCategory.VariantCategoryResponse;
import com.fit.ecommerce.services.VariantCategoryService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${api.prefix}/variant-categories")
@RequiredArgsConstructor
public class VariantCategoryController {

    private final VariantCategoryService variantCategoryService;

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ResponseSuccess<List<VariantCategoryResponse>>> getVariantCategoriesByCategoryId(
            @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(new ResponseSuccess<>(
                OK,
                "Get variant categories by category success",
                variantCategoryService.getVariantCategoriesByCategoryId(categoryId)
        ));
    }

    @PostMapping("/assign-variants")
    public ResponseEntity<ResponseSuccess<Void>> assignVariantsToCategory(
            @Valid @RequestBody SetVariantsForCategoryRequest request
    ) {
        variantCategoryService.setVariantsForCategory(request);

        return ResponseEntity.ok(new ResponseSuccess<>(
                OK,
                "Cập nhật danh sách variant cho danh mục thành công",
                null
        ));
    }
}

