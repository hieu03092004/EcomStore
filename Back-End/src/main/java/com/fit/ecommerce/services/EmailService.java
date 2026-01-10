package com.fit.ecommerce.services;

import com.fit.ecommerce.entities.Cart;
import com.fit.ecommerce.entities.Order;
import com.fit.ecommerce.entities.Voucher;

public interface EmailService {

    void sendVoucher(String to, Voucher voucher, String code);
    
    void sendOrderConfirmation(String to, Order order);

    void sendAbandonedCartReminder(String to, Cart cart);
}
