package com.fit.ecommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fit.ecommerce.entities.Product;
import com.fit.ecommerce.entities.ProductQuestion;

import java.util.Optional;

public interface ProductQuestionRepository  extends JpaRepository<ProductQuestion, Long> {
    Page<ProductQuestion> findByProduct(Product product, Pageable pageable);

    @Query("SELECT pq FROM ProductQuestion pq " +
            "WHERE (:status IS NULL OR pq.status = :status) " +
            "AND (:search IS NULL OR LOWER(pq.content) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(pq.user.fullName) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND (:productId IS NULL OR pq.product.id = :productId)")
    Page<ProductQuestion> findAllWithFilters(
            @Param("status") Boolean status,
            @Param("search") String search,
            @Param("productId") Long productId,
            Pageable pageable);

    @Query("SELECT pq FROM ProductQuestion pq " +
            "JOIN pq.answers pa " +
            "WHERE pa.id = :answerId")
    Optional<ProductQuestion> findByAnswerId(@Param("answerId") Long answerId);
}
