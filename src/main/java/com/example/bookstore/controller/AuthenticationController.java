package com.example.bookstore.controller;

import com.example.bookstore.dto.request.UserRegistrationRequestDto;
import com.example.bookstore.dto.response.UserResponseDto;
import com.example.bookstore.exceptions.RegistrationException;
import com.example.bookstore.mappper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserMapper userMapper;

    @GetMapping("/register")
    public UserResponseDto register(UserRegistrationRequestDto request) throws RegistrationException {
        return new UserResponseDto();
    }

    @GetMapping("/login")
    public UserResponseDto login() {
        return new UserResponseDto();
    }
}
