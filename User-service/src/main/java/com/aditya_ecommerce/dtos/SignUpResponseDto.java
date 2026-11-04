package com.aditya_ecommerce.dtos;

import com.aditya_ecommerce.entity.enums.Roles;
import lombok.Data;

import java.util.UUID;

@Data
public class SignUpResponseDto {
    private UUID id;
    private String email;
    private String name;
    private Roles role;
}
