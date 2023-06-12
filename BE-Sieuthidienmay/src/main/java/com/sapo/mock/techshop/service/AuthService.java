package com.sapo.mock.techshop.service;

import com.sapo.mock.techshop.dto.request.LoginRequest;
import com.sapo.mock.techshop.dto.request.RegisterUserRequest;
import com.sapo.mock.techshop.dto.response.LoginUserInfo;
import com.sapo.mock.techshop.entity.AuthUser;

public interface AuthService {
    LoginUserInfo handleLoginRequest(LoginRequest loginRequest);
    AuthUser findByUsername(String username);
    AuthUser createUser(RegisterUserRequest registerUserRequest);
}
