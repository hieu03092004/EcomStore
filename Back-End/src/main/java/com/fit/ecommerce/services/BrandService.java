package com.fit.ecommerce.services;

import java.util.List;

import com.fit.ecommerce.dtos.request.brand.BrandAddRequest;
import com.fit.ecommerce.dtos.response.base.PageResponse;
import com.fit.ecommerce.dtos.response.brand.BrandResponse;
import com.fit.ecommerce.entities.Brand;

public interface BrandService {

    BrandResponse createBrand(BrandAddRequest request);

    PageResponse<BrandResponse> getBrands(int page, int size, String brandName);

    BrandResponse getBrandById(Long id);

    BrandResponse updateBrand(Long id, BrandAddRequest request);

    void changeStatusBrand(Long id);
    Brand getBrandEntityById(Long id);

//    List<BrandResponse> getBrandsByCategoryId(Long id);
}
