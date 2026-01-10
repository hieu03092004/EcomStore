package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fit.ecommerce.dtos.request.banner.BannerAddRequest;
import com.fit.ecommerce.dtos.response.banner.BannerResponse;
import com.fit.ecommerce.entities.Banner;

@Mapper(componentModel = "spring")
public interface BannerMapper {

    Banner toEntity(BannerAddRequest request);

    @Mapping(source = "staff.id", target = "staffId")
    BannerResponse toResponse(Banner banner);
}
