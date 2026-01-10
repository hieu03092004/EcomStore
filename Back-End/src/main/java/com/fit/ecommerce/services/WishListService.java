package com.fit.ecommerce.services;

import org.springframework.transaction.annotation.Transactional;

import com.fit.ecommerce.dtos.request.wishList.WishListRequest;
import com.fit.ecommerce.dtos.response.wishList.WishListResponse;

import java.util.List;

public interface WishListService {
    @Transactional
    List<WishListResponse> addProductToWishList(WishListRequest request);

    @Transactional
    List<WishListResponse> removeProductFromWishList(WishListRequest request);

    List<WishListResponse> getMyWishList();
}
