package com.fit.ecommerce.services;

import java.util.List;

import com.fit.ecommerce.entities.ProductVariant;

public interface VectorStoreService {
    void indexProductVariant(ProductVariant productVariant);
    void deleteProductVariantIndex(Long productVariantId);
    List<String> searchSimilarProducts(String query, int topK);
    List<Long> searchSimilarProductIds(String query, int topK); // Trả về danh sách product IDs
}

