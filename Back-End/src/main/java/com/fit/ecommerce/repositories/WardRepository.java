package com.fit.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.ecommerce.entities.Ward;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, Integer> {
    // Integer thay vì String
    List<Ward> findByProvince_Id(Integer provinceId); // Đổi từ Code → Id
}