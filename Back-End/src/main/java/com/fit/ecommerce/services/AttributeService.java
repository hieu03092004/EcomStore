package com.fit.ecommerce.services;

import java.util.List;

import com.fit.ecommerce.dtos.response.attribute.AttributeResponse;
import com.fit.ecommerce.entities.Attribute;
import com.fit.ecommerce.entities.Category;

public interface AttributeService {
    List<AttributeResponse> getAttributesActive();
    Attribute getAttributeEntityById(Long id);
}
