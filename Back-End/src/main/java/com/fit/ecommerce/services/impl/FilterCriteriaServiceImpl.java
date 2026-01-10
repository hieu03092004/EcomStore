package com.fit.ecommerce.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fit.ecommerce.configurations.CacheConfig;
import com.fit.ecommerce.dtos.request.filterCriteria.CreateFilterCriteriaRequest;
import com.fit.ecommerce.dtos.request.filterCriteria.SetFilterValuesForCriteriaRequest;
import com.fit.ecommerce.dtos.response.filterCriteria.FilterCriteriaResponse;
import com.fit.ecommerce.dtos.response.filterCriteria.FilterValueResponse;
import com.fit.ecommerce.entities.Category;
import com.fit.ecommerce.entities.FilterCriteria;
import com.fit.ecommerce.entities.FilterValue;
import com.fit.ecommerce.exceptions.ErrorCode;
import com.fit.ecommerce.exceptions.custom.ResourceNotFoundException;
import com.fit.ecommerce.mappers.FilterCriteriaMapper;
import com.fit.ecommerce.repositories.CategoryRepository;
import com.fit.ecommerce.repositories.FilterCriteriaRepository;
import com.fit.ecommerce.repositories.FilterValueRepository;
import com.fit.ecommerce.repositories.ProductFilterValueRepository;
import com.fit.ecommerce.services.CategoryService;
import com.fit.ecommerce.services.FilterCriteriaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterCriteriaServiceImpl implements FilterCriteriaService {

    private final FilterCriteriaRepository filterCriteriaRepository;
    private final FilterValueRepository filterValueRepository;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final FilterCriteriaMapper filterCriteriaMapper;
    private final ProductFilterValueRepository productFilterValueRepository;

    @Override
    @Transactional
    public FilterCriteriaResponse createFilterCriteria(CreateFilterCriteriaRequest request) {
        Category category = categoryService.getCategoryEntityById(request.getCategoryId());

        FilterCriteria filterCriteria = FilterCriteria.builder()
                .name(request.getName())
                .category(category)
                .build();

        FilterCriteria saved = filterCriteriaRepository.save(filterCriteria);

        // Thêm các giá trị nếu có
        if (request.getValues() != null && !request.getValues().isEmpty()) {
            List<FilterValue> filterValues = request.getValues().stream()
                    .map(value -> FilterValue.builder()
                            .filterCriteria(saved)
                            .value(value)
                            .build())
                    .collect(Collectors.toList());
            filterValueRepository.saveAll(filterValues);
        }

        return filterCriteriaMapper.toResponse(saved);
    }

    @Override
    public List<FilterCriteriaResponse> getFilterCriteriaByCategoryId(Long categoryId, String name) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException(ErrorCode.CATEGORY_NOT_FOUND);
        }

        List<FilterCriteria> filterCriteriaList = filterCriteriaRepository.findByCategoryIdAndName(categoryId, name);
        return filterCriteriaMapper.toResponseList(filterCriteriaList);
    }

    @Override
    public List<FilterCriteriaResponse> getFilterCriteriaByCategorySlug(String categorySlug, String name) {
        Category category = categoryRepository.findBySlug(categorySlug)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.CATEGORY_NOT_FOUND));

        List<FilterCriteria> filterCriteriaList = filterCriteriaRepository.findByCategoryIdAndName(category.getId(), name);
        return filterCriteriaMapper.toResponseList(filterCriteriaList);
    }

    @Override
    @Transactional
    public void setFilterValuesForCriteria(SetFilterValuesForCriteriaRequest request) {
        FilterCriteria filterCriteria = getFilterCriteriaEntityById(request.getFilterCriteriaId());

        filterValueRepository.deleteAllByFilterCriteriaId(request.getFilterCriteriaId());

        if (request.getValues() == null || request.getValues().isEmpty()) {
            return;
        }

        List<FilterValue> newFilterValues = request.getValues().stream()
                .map(value -> FilterValue.builder()
                        .filterCriteria(filterCriteria)
                        .value(value)
                        .build())
                .collect(Collectors.toList());

        filterValueRepository.saveAll(newFilterValues);
    }

    @Override
    public List<FilterValueResponse> getFilterValuesByCriteriaId(Long filterCriteriaId, String value) {
        if (!filterCriteriaRepository.existsById(filterCriteriaId)) {
            throw new ResourceNotFoundException(ErrorCode.FILTER_CRITERIA_NOT_FOUND);
        }

        List<FilterValue> filterValues = filterValueRepository.findByFilterCriteriaIdAndValue(filterCriteriaId, value);
        return filterCriteriaMapper.toFilterValueResponseList(filterValues);
    }

    @Override
    public List<FilterValueResponse> getFilterValuesByProductId(Long productId) {
        List<FilterValue> filterValues = productFilterValueRepository.findFilterValuesByProductId(productId);
        return filterCriteriaMapper.toFilterValueResponseList(filterValues);
    }

    @Override
    @Transactional
    public void deleteFilterCriteria(Long id) {
        FilterCriteria filterCriteria = getFilterCriteriaEntityById(id);
        filterCriteriaRepository.delete(filterCriteria);
    }

    private FilterCriteria getFilterCriteriaEntityById(Long id) {
        return filterCriteriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.FILTER_CRITERIA_NOT_FOUND));
    }
}

