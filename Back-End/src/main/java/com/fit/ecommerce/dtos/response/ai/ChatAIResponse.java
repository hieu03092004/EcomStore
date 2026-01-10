package com.fit.ecommerce.dtos.response.ai;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fit.ecommerce.dtos.response.product.ProductResponse;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatAIResponse {
    private String message;
    private String role; // "assistant" hoặc "system"
    private List<ProductResponse> products; // Danh sách sản phẩm được AI tìm thấy
}

