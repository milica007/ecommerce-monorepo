package com.hc.user_management_service.service;

import com.hc.user_management_service.dto.AuthDTO;
import com.hc.user_management_service.dto.LoginDTO;
import com.hc.user_management_service.dto.RegisterDTO;
import com.hc.user_management_service.dto.UserDTO;
import com.hc.user_management_service.exception.NotFoundException;
import com.hc.user_management_service.model.Role;
import com.hc.user_management_service.model.RoleName;
import com.hc.user_management_service.model.User;
import com.hc.user_management_service.repository.RoleRepository;
import com.hc.user_management_service.repository.UserRepository;
import com.hc.user_management_service.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthDTO login(LoginDTO loginDTO) {
        var user = userRepository.findByEmail(loginDTO.email())
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.email(),
                loginDTO.password()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var token = jwtTokenProvider.generateToken(authentication);

        return new AuthDTO(token);
    }

    @Override
    public UserDTO register(RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.email()) || userRepository.existsByUsername(registerDTO.username())) {
            throw new IllegalArgumentException("Username or Email already exists!");
        }

        Set<Role> roles = registerDTO.roles() == null || registerDTO.roles().isEmpty()
                ? Set.of(roleRepository.findByName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role not found")))
                : registerDTO.roles().stream()
                .map(role -> roleRepository.findByName(role).orElseThrow(() -> new RuntimeException("Role not found")))
                .collect(Collectors.toSet());

        User user = User.builder()
                .username(registerDTO.username())
                .email(registerDTO.email())
                .password(passwordEncoder.encode(registerDTO.password()))
                .roles(roles)
                .build();

        userRepository.save(user);

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles()
        );
    }
}
