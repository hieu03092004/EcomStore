package com.fit.ecommerce.services;

import java.time.LocalDate;
import java.util.List;

import com.fit.ecommerce.dtos.request.supplier.SupplierRequest;
import com.fit.ecommerce.dtos.response.base.PageResponse;
import com.fit.ecommerce.dtos.response.supplier.SupplierResponse;
import com.fit.ecommerce.entities.Supplier;

public interface SupplierService {

    SupplierResponse createSupplier(SupplierRequest request);

    SupplierResponse getSupplierById(Long id);

    PageResponse<SupplierResponse> getSuppliers(
            int page, int size,
            String name, String phone, String address, Boolean status,
            LocalDate startDate, LocalDate endDate
    );

    SupplierResponse updateSupplier(Long id, SupplierRequest request);

    void changeStatusSupplier(Long id);

    Supplier getSupplierEntityById(Long id);

}