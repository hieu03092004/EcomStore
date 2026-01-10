// impl
package com.fit.ecommerce.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fit.ecommerce.dtos.request.variant.VariantAddRequest;
import com.fit.ecommerce.dtos.response.base.PageResponse;
import com.fit.ecommerce.dtos.response.variant.VariantResponse;
import com.fit.ecommerce.entities.Variant;
import com.fit.ecommerce.entities.VariantValue;
import com.fit.ecommerce.exceptions.ErrorCode;
import com.fit.ecommerce.exceptions.custom.ResourceNotFoundException;
import com.fit.ecommerce.mappers.VariantMapper;
import com.fit.ecommerce.repositories.VariantCategoryRepository;
import com.fit.ecommerce.repositories.VariantRepository;
import com.fit.ecommerce.services.CategoryService;
import com.fit.ecommerce.services.VariantService;
import com.fit.ecommerce.services.VariantValueService;
import com.fit.ecommerce.utils.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VariantServiceImpl implements VariantService {

    private final VariantRepository variantRepository;
    private final VariantMapper variantMapper;
    private final VariantValueService variantValueService;
    private final CategoryService categoryService;
    private final VariantCategoryRepository variantCategoryRepository;

    @Override
    public PageResponse<VariantResponse> getVariants(int page, int size, String variantName) {
        page = Math.max(0, page - 1);
        Pageable pageable = PageRequest.of(page, size);
        Page<Variant> variantPage;

        if (variantName != null && !variantName.isBlank()) {
            variantPage = variantRepository.findByNameContainingIgnoreCase(variantName, pageable);
        } else {
            variantPage = variantRepository.findAll(pageable);
        }
        return PageResponse.fromPage(variantPage,variantMapper::toResponse);
    }

    @Override
    public VariantResponse getVariantById(Long id) {
        Variant variant = findVariantOrThrow(id);
        return variantMapper.toResponse(variant);
    }

    @Override
    public VariantResponse createVariant(VariantAddRequest request) {
        Variant variant = new Variant();
        mapVariantFields(variant, request);
        variant = variantRepository.save(variant);

        if (request.getVariantValues() != null && !request.getVariantValues().isEmpty()) {
            List<VariantValue> values = variantValueService.createValues(request.getVariantValues(), variant);
            variant.setVariantValues(values);
        }

        return variantMapper.toResponse(variant);
    }

    @Override
    public VariantResponse updateVariant(Long id, VariantAddRequest request) {
        Variant variant = findVariantOrThrow(id);

        mapVariantFields(variant, request);
        variant = variantRepository.save(variant);

        if (request.getVariantValues() != null && !request.getVariantValues().isEmpty()) {
            List<VariantValue> newValues = variantValueService.updateValue(request.getVariantValues(), variant);
            variant.getVariantValues().addAll(newValues);
        }

        return variantMapper.toResponse(variant);
    }

    @Override
    public void changeStatusVariant(Long id) {
        Variant variant = findVariantOrThrow(id);
        variant.setStatus(!variant.getStatus());
        variantRepository.save(variant);
    }

    @Override
    public List<VariantResponse> getVariantsByCategory( Long id) {
        List<Variant> variants = variantCategoryRepository.findByStatusAndCategoryId(true,id);
        return variants.stream()
                .map(variantMapper::toResponse)
                .toList();
    }

    @Override
    public List<VariantResponse> getVariantsByCategorySlug(String slug) {
        List<Variant> variants = variantCategoryRepository.findByStatusAndCategorySlug(true,slug);
        return variants.stream()
                .map(variantMapper::toResponse)
                .toList();
    }

    private Variant findVariantOrThrow(Long id) {
        return variantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.VARIANT_NOT_FOUND));
    }


    private void mapVariantFields(Variant variant, VariantAddRequest request) {
        variant.setName(request.getName());
        variant.setSlug(StringUtils.normalizeString( request.getName()));
        if (request.getStatus() != null) {
            variant.setStatus(request.getStatus());
        }
    }
}