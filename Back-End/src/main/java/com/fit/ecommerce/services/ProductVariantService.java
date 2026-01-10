package com.fit.ecommerce.services;

import java.util.List;

import com.fit.ecommerce.dtos.request.product.ProductVariantPromotionRequest;
import com.fit.ecommerce.dtos.response.product.ProductVariantDescriptionResponse;
import com.fit.ecommerce.dtos.response.product.ProductVariantPromotionResponse;

public interface ProductVariantService {

    List<ProductVariantDescriptionResponse> getAllSkusForPromotion(Long productId);
    List<ProductVariantPromotionResponse> getProductsVariantPromotions(ProductVariantPromotionRequest productVariantPromotionRequest);
}