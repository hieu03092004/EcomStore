package com.fit.ecommerce.dtos.response.product;

import com.fit.ecommerce.dtos.response.attribute.AttributeResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductAttributeResponse {
    private Long id;
    private String value;
    private AttributeResponse attribute;
}
