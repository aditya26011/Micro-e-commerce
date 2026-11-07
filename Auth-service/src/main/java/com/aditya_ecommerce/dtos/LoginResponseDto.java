package com.aditya_ecommerce.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class LoginResponseDto {
    private String token;
    private UUID userId;
}
