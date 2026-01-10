package com.fit.ecommerce.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.fit.ecommerce.entities.User;
import com.fit.ecommerce.exceptions.ErrorCode;
import com.fit.ecommerce.exceptions.custom.ResourceNotFoundException;
import com.fit.ecommerce.repositories.UserRepository;
import com.fit.ecommerce.services.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserEntityById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
