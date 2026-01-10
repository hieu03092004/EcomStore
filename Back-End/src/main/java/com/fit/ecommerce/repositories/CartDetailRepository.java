package com.fit.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.ecommerce.entities.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
}
