package com.fit.ecommerce.services;

import java.util.List;

import com.fit.ecommerce.dtos.response.shipper.ShipperResponse;

public interface ShipperService {
    List<ShipperResponse> getAllActiveShippers();
    List<ShipperResponse> getAllShippers();
}
