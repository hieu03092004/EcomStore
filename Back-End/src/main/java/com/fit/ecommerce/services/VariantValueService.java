package com.fit.ecommerce.services;

import java.util.List;

import com.fit.ecommerce.dtos.request.variant.VariantValueAddRequest;
import com.fit.ecommerce.dtos.response.variant.VariantValueResponse;
import com.fit.ecommerce.entities.Variant;
import com.fit.ecommerce.entities.VariantValue;

public interface VariantValueService {
    List<VariantValueResponse> getVariantValueByVariantId(Long id);

    void changeStatusVariantValue(Long id);

    List<VariantValue> createValues(List<VariantValueAddRequest> variantValues, Variant variant);

    List<VariantValue> updateValue(List<VariantValueAddRequest> variantValues, Variant variant);
    VariantValue getVariantValueEntityById(Long id);
}
