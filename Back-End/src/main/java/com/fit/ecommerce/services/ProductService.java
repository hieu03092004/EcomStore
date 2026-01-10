package com.fit.ecommerce.services;

import java.util.List;
import java.util.Map;

import com.fit.ecommerce.dtos.request.product.ProductAddRequest;
import com.fit.ecommerce.dtos.request.product.ProductUpdateRequest;
import com.fit.ecommerce.dtos.response.base.PageResponse;
import com.fit.ecommerce.dtos.response.product.ProductResponse;
import com.fit.ecommerce.entities.Product;

public interface ProductService {
    void createProduct(ProductAddRequest productAddRequest);
    PageResponse<ProductResponse> getAllProducts(int page, int size, String keyword, Long brandId, Long categoryId, Boolean status, Double minPrice, Double maxPrice);
    ProductResponse getProductById(Long id);
    ProductResponse getProductBySlug(String slug);
    ProductResponse updateProduct(Long id, ProductUpdateRequest productUpdateRequest);
    void changeStatusProduct(Long id);

    Product getProductEntityById(Long id);

    Product getProductEntityBySlug(String slug);

    PageResponse<ProductResponse> searchProductForUser(String categorySlug, int page, int size, Map<String, String> filters);
}
