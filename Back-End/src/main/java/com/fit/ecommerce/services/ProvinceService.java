package com.fit.ecommerce.services;

import java.util.List;

import com.fit.ecommerce.dtos.response.province.ProvinceResponse;
import com.fit.ecommerce.dtos.response.ward.WardResponse;

public interface ProvinceService {
    List<ProvinceResponse> getAllProvinces();
    List<WardResponse> getWardsByProvince(Integer provinceId); // Đổi String → Integer
    List<WardResponse> getAllWards();
}