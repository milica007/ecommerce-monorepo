package com.hc.user_management_service.controller;

import com.hc.user_management_service.dto.AuthDTO;
import com.hc.user_management_service.dto.LoginDTO;
import com.hc.user_management_service.dto.RegisterDTO;
import com.hc.user_management_service.dto.UserDTO;
import com.hc.user_management_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/auth")
public class AuthController implements AuthApi {

    private final AuthService service;

    @Override
    public UserDTO register(RegisterDTO registerDTO) {
        return service.register(registerDTO);
    }

    @Override
    public AuthDTO login(LoginDTO loginDTO) {
        return service.login(loginDTO);
    }
}