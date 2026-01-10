package com.fit.ecommerce.services;

import java.util.List;

import com.fit.ecommerce.dtos.request.variant.VariantAddRequest;
import com.fit.ecommerce.dtos.response.base.PageResponse;
import com.fit.ecommerce.dtos.response.variant.VariantResponse;

public interface VariantService {
    VariantResponse createVariant(VariantAddRequest request);

    PageResponse<VariantResponse> getVariants(int page, int size, String variantName);

    VariantResponse getVariantById(Long id);

    VariantResponse updateVariant(Long id, VariantAddRequest request);

    void changeStatusVariant(Long id);

    List<VariantResponse> getVariantsByCategory(Long id);

    List<VariantResponse> getVariantsByCategorySlug(String slug);
}
