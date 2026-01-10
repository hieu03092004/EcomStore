package com.fit.ecommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fit.ecommerce.entities.Brand;
import com.fit.ecommerce.entities.Category;
import com.fit.ecommerce.entities.CategoryBrand;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryBrandRepository extends JpaRepository<CategoryBrand, Long> {

    void deleteAllByCategoryId(Long categoryId);

    @Query("SELECT cb.brand FROM CategoryBrand cb " +
            "WHERE cb.category.id = :categoryId " +
            "AND (:brandName IS NULL OR cb.brand.name LIKE %:brandName%)")
    List<Brand> findBrandsByCategoryIdAndName(
            @Param("categoryId") Long categoryId,
            @Param("brandName") String brandName
    );

    @Query("SELECT cb.category FROM CategoryBrand cb " +
            "WHERE cb.brand.id = :brandId " +
            "AND (:categoryName IS NULL OR cb.category.name LIKE %:categoryName%)")
    List<Category> findCategoriesByBrandIdAndName(
            @Param("brandId") Long brandId,
            @Param("categoryName") String categoryName
    );

    @Query("SELECT cb.brand FROM CategoryBrand cb " +
            "WHERE cb.category.slug = :slug ")
    List<Brand> findBrandsByCategorySlug(
            @Param("slug") String slug
    );
}