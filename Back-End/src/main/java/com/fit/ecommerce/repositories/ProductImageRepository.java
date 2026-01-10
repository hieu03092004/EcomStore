package com.fit.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.ecommerce.entities.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
