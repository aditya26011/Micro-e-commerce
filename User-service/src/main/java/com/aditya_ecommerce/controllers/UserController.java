package com.aditya_ecommerce.controllers;

import com.aditya_ecommerce.dtos.SignUpRequestDto;
import com.aditya_ecommerce.dtos.SignUpResponseDto;
import com.aditya_ecommerce.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    ResponseEntity<SignUpResponseDto> register(@RequestBody @Valid SignUpRequestDto signUpRequestDto){
        SignUpResponseDto signUpResponseDto= userService.register(signUpRequestDto);
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);

    }
}
