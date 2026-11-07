package com.aditya_ecommerce.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotNull(message = "email cannot be null")
    @Email
    private String email;

    @NotNull(message = "password is required")
    private String password;
}
