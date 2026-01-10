package com.fit.ecommerce.services;

import java.util.List;

import com.fit.ecommerce.dtos.response.base.PageResponse;
import com.fit.ecommerce.dtos.response.product.ProductResponse;
import com.fit.ecommerce.entities.Product;

public interface ProductSearchService {
    PageResponse<ProductResponse> searchProducts(
            String query,
            int page,
            int size,
            String sortBy
    );
    

    List<String> getAutoCompleteSuggestions(String query, int limit);
    
    void indexProduct(Product product);
    
    void deleteProduct(Long productId);
    
    void reindexAllProducts();
}

