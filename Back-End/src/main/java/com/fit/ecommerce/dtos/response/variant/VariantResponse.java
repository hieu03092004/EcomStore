package com.fit.ecommerce.dtos.response.variant;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import com.fit.ecommerce.dtos.response.category.CategoryResponse;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariantResponse {
    private Long id;
    private String name;
    private boolean status;
    private String slug;
    private List<VariantValueResponse> variantValues;
}
