package com.aditya_ecommerce.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequestDto {
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    private String phoneNumber;
    private String address;

    @NotBlank(message = "password is required")
    private String password;
}
