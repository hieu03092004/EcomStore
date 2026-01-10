package com.fit.ecommerce.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fit.ecommerce.dtos.request.brand.BrandAddRequest;
import com.fit.ecommerce.dtos.response.base.PageResponse;
import com.fit.ecommerce.dtos.response.brand.BrandResponse;
import com.fit.ecommerce.entities.Brand;
import com.fit.ecommerce.exceptions.ErrorCode;
import com.fit.ecommerce.exceptions.custom.ConflictException;
import com.fit.ecommerce.exceptions.custom.ResourceNotFoundException;
import com.fit.ecommerce.mappers.BrandMapper;
import com.fit.ecommerce.repositories.BrandRepository;
import com.fit.ecommerce.services.BrandService;
import com.fit.ecommerce.services.CategoryBrandService;
import com.fit.ecommerce.utils.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final CategoryBrandService categoryBrandService;

    @Override
    @Transactional
    public BrandResponse createBrand(BrandAddRequest request) {
        validateBrandName(request.getName(), null);

        Brand brand = new Brand();
        mapRequestToBrand(brand, request);
        brandRepository.save(brand);

        return brandMapper.toResponse(brand);
    }

    @Override
    public PageResponse<BrandResponse> getBrands(int page, int size, String brandName) {
        page = Math.max(0, page - 1);
        Pageable pageable = PageRequest.of(page, size);
        Page<Brand> brandPage ;

        if (brandName != null && !brandName.isBlank()) {
            brandPage = brandRepository.findByNameContainingIgnoreCase(brandName, pageable);
        } else {
            brandPage = brandRepository.findAll(pageable);
        }
        return PageResponse.fromPage(brandPage, brandMapper::toResponse);
    }

    @Override
    public BrandResponse getBrandById(Long id) {
        return brandMapper.toResponse(getBrandEntityById(id));
    }

    @Override
    @Transactional
    public BrandResponse updateBrand(Long id, BrandAddRequest request) {
        Brand brand = getBrandEntityById(id);
        validateBrandName(request.getName(), brand);
        mapRequestToBrand(brand, request);
        brandRepository.save(brand);
        return brandMapper.toResponse(brand);
    }

    @Override
    public void changeStatusBrand(Long id) {
        Brand brand = getBrandEntityById(id);
        brand.setStatus(!brand.getStatus());
        brandRepository.save(brand);
    }

    @Override
    public Brand getBrandEntityById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.BRAND_NOT_FOUND));
    }

    private void validateBrandName(String name, Brand existingBrand) {
        if (existingBrand == null && brandRepository.existsByName(name)) {
            throw new ConflictException(ErrorCode.BRAND_NAME_EXISTS);
        }
        if (existingBrand != null && !name.equals(existingBrand.getName()) &&
                brandRepository.existsByName(name)) {
            throw new ConflictException(ErrorCode.BRAND_NAME_EXISTS);
        }
    }

    private void mapRequestToBrand(Brand brand, BrandAddRequest request) {
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setImage(request.getImage());
        brand.setOrigin(request.getOrigin());
        brand.setStatus(Boolean.TRUE.equals(request.getStatus()));
        brand.setSlug(StringUtils.normalizeString(request.getName()));
    }
}
