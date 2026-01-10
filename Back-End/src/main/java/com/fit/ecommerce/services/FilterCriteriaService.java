package com.fit.ecommerce.services;

import java.util.List;

import com.fit.ecommerce.dtos.request.filterCriteria.CreateFilterCriteriaRequest;
import com.fit.ecommerce.dtos.request.filterCriteria.SetFilterValuesForCriteriaRequest;
import com.fit.ecommerce.dtos.response.filterCriteria.FilterCriteriaResponse;
import com.fit.ecommerce.dtos.response.filterCriteria.FilterValueResponse;

public interface FilterCriteriaService {

    FilterCriteriaResponse createFilterCriteria(CreateFilterCriteriaRequest request);

    List<FilterCriteriaResponse> getFilterCriteriaByCategoryId(Long categoryId, String name);

    List<FilterCriteriaResponse> getFilterCriteriaByCategorySlug(String categorySlug, String name);

    void setFilterValuesForCriteria(SetFilterValuesForCriteriaRequest request);

    List<FilterValueResponse> getFilterValuesByCriteriaId(Long filterCriteriaId, String value);

    List<FilterValueResponse> getFilterValuesByProductId(Long productId);

    void deleteFilterCriteria(Long id);
}

