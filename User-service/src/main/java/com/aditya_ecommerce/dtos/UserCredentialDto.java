package com.aditya_ecommerce.dtos;

import com.aditya_ecommerce.entity.enums.Roles;
import lombok.Data;

import java.util.UUID;

@Data
public class UserCredentialDto {

    private UUID userId;
    private String email;
    private String passwordHash;
    private Roles roles;
}
