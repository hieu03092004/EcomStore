package com.fit.ecommerce.services;

import org.springframework.transaction.annotation.Transactional;

import com.fit.ecommerce.dtos.request.address.AddressRequest;
import com.fit.ecommerce.dtos.response.address.AddressResponse;

import java.util.List;

public interface AddressService {
    AddressResponse addAddress(Long customerId, AddressRequest request);
    AddressResponse updateAddress(Long customerId, Long addressId, AddressRequest request);
    void deleteAddress(Long customerId, Long addressId);

    AddressResponse setDefaultAddress(Long customerId, Long addressId);

    List<AddressResponse> getAddressesByCustomer(Long customerId);

    AddressResponse getAddressById(Long customerId, Long addressId);
}
