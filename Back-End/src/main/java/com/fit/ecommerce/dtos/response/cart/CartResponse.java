package com.fit.ecommerce.dtos.response.cart;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import com.fit.ecommerce.entities.BaseEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    private Long cartId;
    private Long userId;
    private List<CartDetailResponse> items;
    private double totalPrice;
    private LocalDateTime modifiedAt;
}
