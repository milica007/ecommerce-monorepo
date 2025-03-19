package com.hc.user_management_service.service;

import com.hc.user_management_service.dto.AuthDTO;
import com.hc.user_management_service.dto.LoginDTO;
import com.hc.user_management_service.dto.RegisterDTO;
import com.hc.user_management_service.dto.UserDTO;

public interface AuthService {
    AuthDTO login(LoginDTO loginDto);
    UserDTO register(RegisterDTO registerDTO);
}
