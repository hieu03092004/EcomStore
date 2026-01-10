package com.fit.ecommerce.services;


import java.time.LocalDate;
import java.util.List;

import com.fit.ecommerce.dtos.request.voucher.VoucherAddRequest;
import com.fit.ecommerce.dtos.request.voucher.VoucherUpdateRequest;
import com.fit.ecommerce.dtos.response.base.PageResponse;
import com.fit.ecommerce.dtos.response.voucher.VoucherAvailableResponse;
import com.fit.ecommerce.dtos.response.voucher.VoucherResponse;
import com.fit.ecommerce.entities.Voucher;

public interface VoucherService {
    VoucherResponse createVoucher( VoucherAddRequest request);

    VoucherResponse getVoucherById(Long id);

    PageResponse<VoucherResponse> getAllVouchers(int page, int limit, String name, String type, Boolean active, LocalDate startDate, LocalDate endDate);

    VoucherResponse updateVoucher(Long id,  VoucherUpdateRequest request);

    void changeStatusVoucher(Long id);

    void sendVoucherToCustomers(Long id);

    Voucher getVoucherEntityById(Long id);

    List<VoucherAvailableResponse> getAvailableVouchersForCustomer();

    List<VoucherAvailableResponse> getAvailableVouchersForCustomerById(Long customerId);
}
