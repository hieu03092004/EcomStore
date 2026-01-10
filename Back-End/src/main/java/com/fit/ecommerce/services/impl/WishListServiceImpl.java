package com.fit.ecommerce.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fit.ecommerce.dtos.request.wishList.WishListRequest;
import com.fit.ecommerce.dtos.response.wishList.WishListResponse;
import com.fit.ecommerce.entities.Customer;
import com.fit.ecommerce.entities.Product;
import com.fit.ecommerce.entities.User;
import com.fit.ecommerce.entities.WishList;
import com.fit.ecommerce.exceptions.ErrorCode;
import com.fit.ecommerce.exceptions.custom.InvalidParamException;
import com.fit.ecommerce.exceptions.custom.ResourceNotFoundException;
import com.fit.ecommerce.mappers.WishListMapper;
import com.fit.ecommerce.repositories.ProductRepository;
import com.fit.ecommerce.repositories.WishListRepository;
import com.fit.ecommerce.services.WishListService;
import com.fit.ecommerce.utils.SecurityUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final SecurityUtils securityUtil;
    private final WishListRepository wishListRepository;
    private final ProductRepository productRepository;
    private final WishListMapper wishListMapper;

    private Customer getCurrentCustomer() {
        User user = securityUtil.getCurrentUser();
        if (!(user instanceof Customer)) {
            throw new InvalidParamException(ErrorCode.INVALID_PARAMETER);
        }
        return (Customer) user;
    }

    private Product findProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    @Transactional
    @Override
    public List<WishListResponse> addProductToWishList(WishListRequest request) {
        Long productId = request.getProductId();
        Customer customer = getCurrentCustomer();
        Product product = findProduct(productId);
        Optional<WishList> existingWishList = wishListRepository.findByCustomer_IdAndProduct_Id(
                customer.getId(), productId);
        if (existingWishList.isEmpty()) {
            WishList newWishList = WishList.builder()
                    .customer(customer)
                    .product(product)
                    .build();
            wishListRepository.save(newWishList);
        }
        return getMyWishList();
    }

    @Transactional
    @Override
    public List<WishListResponse> removeProductFromWishList(WishListRequest request) {
        Long productId = request.getProductId();
        Customer customer = getCurrentCustomer();
        wishListRepository.deleteByCustomer_IdAndProduct_Id(customer.getId(), productId);
        return getMyWishList();
    }

    @Override
    public List<WishListResponse> getMyWishList() {
        Customer customer = getCurrentCustomer();
        List<WishList> wishLists = wishListRepository.findAllByCustomer_Id(customer.getId());
        List<Product> products = wishLists.stream()
                .map(WishList::getProduct)
                .collect(Collectors.toList());
        return wishListMapper.toResponseList(products);
    }
}