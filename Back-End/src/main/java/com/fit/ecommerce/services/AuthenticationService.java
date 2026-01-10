package com.fit.ecommerce.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.io.IOException;

import com.fit.ecommerce.dtos.request.authentication.LoginRequest;
import com.fit.ecommerce.dtos.request.authentication.RegisterRequest;
import com.fit.ecommerce.dtos.response.authentication.LoginResponse;
import com.fit.ecommerce.dtos.response.authentication.RefreshTokenResponse;
import com.fit.ecommerce.dtos.response.user.UserProfileResponse;

public interface AuthenticationService {
    LoginResponse staffLogin(LoginRequest loginRequest);

    RefreshTokenResponse refreshToken(HttpServletRequest request);

    void logout(HttpServletRequest request);

    String generateAuthUrl(String loginType, String redirectUri);

    LoginResponse socialLoginCallback(String loginType, String code, String redirectUri) throws IOException;

    LoginResponse userLogin(LoginRequest loginRequest);

    UserProfileResponse getProfile();

    void register(@Valid RegisterRequest registerRequest);
}
