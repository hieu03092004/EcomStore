package com.fit.ecommerce.services;


import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

import com.fit.ecommerce.dtos.request.address.AddressRequest;
import com.fit.ecommerce.dtos.request.customer.CustomerAddRequest;
import com.fit.ecommerce.dtos.request.customer.CustomerProfileRequest;
import com.fit.ecommerce.dtos.response.address.AddressResponse;
import com.fit.ecommerce.dtos.response.base.PageResponse;
import com.fit.ecommerce.dtos.response.customer.CustomerResponse;
import com.fit.ecommerce.entities.Customer;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerAddRequest customerAddRequest);
    CustomerResponse getCustomerById(long id);
    PageResponse<CustomerResponse> getAllCustomers(int page, int limit,
                                                         String name, String phone, String email, Boolean status, LocalDate startDate, LocalDate endDate, String rank);
    CustomerResponse updateCustomer(long id, CustomerProfileRequest request);
    void deleteCustomer(long id);
    void changeStatusCustomer(Long id);
    Customer getCustomerEntityById( Long id);
    Customer getCustomerEntityByEmail(String email);

    void updateExpoPushToken(@NotBlank(message = "Expo push token is required") String expoPushToken);

    // Address management for admin
    AddressResponse addAddressForCustomer(Long customerId, AddressRequest request);
    AddressResponse updateAddressForCustomer(Long customerId, Long addressId, AddressRequest request);
    void deleteAddressForCustomer(Long customerId, Long addressId);

    CustomerResponse getCustomerByPhone(String phone);
}
