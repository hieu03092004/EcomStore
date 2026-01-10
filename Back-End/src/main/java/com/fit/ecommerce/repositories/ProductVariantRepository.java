package com.fit.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.ecommerce.entities.ProductVariant;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    List<ProductVariant> findByIdIn(List<Long> ids);
}
