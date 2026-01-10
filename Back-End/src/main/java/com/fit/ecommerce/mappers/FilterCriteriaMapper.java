package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fit.ecommerce.dtos.response.filterCriteria.FilterCriteriaResponse;
import com.fit.ecommerce.dtos.response.filterCriteria.FilterValueResponse;
import com.fit.ecommerce.entities.FilterCriteria;
import com.fit.ecommerce.entities.FilterValue;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FilterCriteriaMapper {

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "filterValues", source = "filterValues")
    FilterCriteriaResponse toResponse(FilterCriteria filterCriteria);

    List<FilterCriteriaResponse> toResponseList(List<FilterCriteria> filterCriteriaList);

    @Mapping(target = "filterCriteriaId", source = "filterCriteria.id")
    FilterValueResponse toResponse(FilterValue filterValue);

    List<FilterValueResponse> toFilterValueResponseList(List<FilterValue> filterValues);
}

