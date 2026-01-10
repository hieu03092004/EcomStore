package com.fit.ecommerce.services;

import com.fit.ecommerce.entities.Chat;
import com.fit.ecommerce.entities.User;

public interface UserService {
    User getUserEntityById(Long userId);
}
