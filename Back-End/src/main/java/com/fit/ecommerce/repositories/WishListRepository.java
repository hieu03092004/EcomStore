package com.fit.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fit.ecommerce.entities.WishList;

import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    Optional<WishList> findByCustomer_IdAndProduct_Id(Long customerId, Long productId);

    List<WishList> findAllByCustomer_Id(Long customerId);

    void deleteByCustomer_IdAndProduct_Id(Long customerId, Long productId);
}