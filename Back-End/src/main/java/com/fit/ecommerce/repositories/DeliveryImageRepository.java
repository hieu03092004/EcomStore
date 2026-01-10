package com.fit.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.ecommerce.entities.DeliveryImage;

public interface DeliveryImageRepository extends JpaRepository<DeliveryImage, Long> {
}