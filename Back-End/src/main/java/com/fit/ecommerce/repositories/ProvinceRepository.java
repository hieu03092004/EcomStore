package com.fit.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.ecommerce.entities.Province;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    // Integer thay vì String
}