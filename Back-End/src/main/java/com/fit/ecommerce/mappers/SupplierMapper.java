package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.fit.ecommerce.dtos.request.supplier.SupplierRequest;
import com.fit.ecommerce.dtos.response.supplier.SupplierResponse;
import com.fit.ecommerce.entities.Supplier;

@Mapper(
        componentModel = "spring"
)
public interface SupplierMapper {

    Supplier toSupplier(SupplierRequest request);

    SupplierResponse toResponse(Supplier supplier);

    void updateFromRequest(SupplierRequest request, @MappingTarget Supplier supplier);
}