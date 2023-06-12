package com.sapo.mock.techshop.controller;

import com.sapo.mock.techshop.dto.request.LoginRequest;
import com.sapo.mock.techshop.dto.response.GeneralResponse;
import com.sapo.mock.techshop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public GeneralResponse<?> userLogin(@RequestBody LoginRequest loginRequest) {
        return GeneralResponse.ok(authService.handleLoginRequest(loginRequest));
    }
}
