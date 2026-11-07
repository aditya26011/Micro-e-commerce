package com.aditya_ecommerce.client;

import com.aditya_ecommerce.dtos.UserCredentialDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "User-service",url = "http://localhost:8081")
public interface UserServiceClient {

    @GetMapping("/internal/users/{email}/credentials")
    UserCredentialDto getUserByEmail(@PathVariable(name = "email") String email);
}
