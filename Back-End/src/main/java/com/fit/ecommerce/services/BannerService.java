package com.fit.ecommerce.services;

import java.time.LocalDate;
import java.util.List;

import com.fit.ecommerce.dtos.request.banner.BannerAddRequest;
import com.fit.ecommerce.dtos.request.banner.BannerUpdateRequest;
import com.fit.ecommerce.dtos.response.banner.BannerResponse;
import com.fit.ecommerce.dtos.response.base.PageResponse;

public interface BannerService {
    BannerResponse getBannerById(Long id);

    BannerResponse addBanner(BannerAddRequest request);

    BannerResponse updateBanner(Long id, BannerUpdateRequest request);

    PageResponse<BannerResponse> getAllBanners(int page, int size, LocalDate startDate, LocalDate endDate, Boolean isActive);

    List<BannerResponse> getBannerToDisplay();
}
