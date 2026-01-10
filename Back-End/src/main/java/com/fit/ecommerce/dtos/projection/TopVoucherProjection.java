package com.fit.ecommerce.dtos.projection;

public interface TopVoucherProjection {
    Long getVoucherId();
    String getVoucherCode();
    String getVoucherName();
    Long getUsageCount();
    Double getTotalDiscountAmount();
}
