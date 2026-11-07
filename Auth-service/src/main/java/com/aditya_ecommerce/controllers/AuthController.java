package com.aditya_ecommerce.controllers;

import com.aditya_ecommerce.dtos.LoginRequestDto;
import com.aditya_ecommerce.dtos.LoginResponseDto;
import com.aditya_ecommerce.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto){
        LoginResponseDto Login=authService.login(loginRequestDto);
        return new ResponseEntity<>(Login, HttpStatus.CREATED);

    }
}
