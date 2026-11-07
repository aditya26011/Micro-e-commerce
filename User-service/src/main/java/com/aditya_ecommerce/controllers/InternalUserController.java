package com.aditya_ecommerce.controllers;

import com.aditya_ecommerce.dtos.UserCredentialDto;
import com.aditya_ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class InternalUserController {

    private final UserService userService;

    @GetMapping("/users/{email}/credentials")
    UserCredentialDto getUserByEmail(@PathVariable(name = "email") String email){
        return userService.getUserByEmail(email);
    }
}
