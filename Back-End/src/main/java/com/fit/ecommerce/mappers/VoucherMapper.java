package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fit.ecommerce.dtos.request.voucher.VoucherAddRequest;
import com.fit.ecommerce.dtos.response.voucher.RankVoucherResponse;
import com.fit.ecommerce.dtos.response.voucher.VoucherAvailableResponse;
import com.fit.ecommerce.dtos.response.voucher.VoucherCustomerResponse;
import com.fit.ecommerce.dtos.response.voucher.VoucherResponse;
import com.fit.ecommerce.entities.Ranking;
import com.fit.ecommerce.entities.Voucher;
import com.fit.ecommerce.entities.VoucherCustomer;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RankingMapper.class})
public interface VoucherMapper {

    @Mapping(target = "code", ignore = true)
    Voucher toVoucher(VoucherAddRequest voucherAddRequest);

    @Mapping(target = "voucherCustomers", source = "voucherCustomers")
    @Mapping(target = "ranking", source = "ranking")
    VoucherResponse toResponse(Voucher voucher);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerName", source = "customer.fullName")
    @Mapping(target = "email", source = "customer.email")
    VoucherCustomerResponse toVoucherCustomerResponse(VoucherCustomer voucherCustomer);

    VoucherAvailableResponse toVoucherAvailableResponse(Voucher voucher);

    List<VoucherCustomerResponse> toVoucherCustomerResponses(List<VoucherCustomer> voucherCustomers);

}
