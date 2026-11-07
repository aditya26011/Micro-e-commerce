package com.aditya_ecommerce.service;

import com.aditya_ecommerce.client.UserServiceClient;
import com.aditya_ecommerce.dtos.LoginRequestDto;
import com.aditya_ecommerce.dtos.LoginResponseDto;
import com.aditya_ecommerce.dtos.UserCredentialDto;
import com.aditya_ecommerce.exception.ResourceNotFoundException;
import com.aditya_ecommerce.exception.ServiceNotAvailable;
import com.aditya_ecommerce.repository.AuthRepository;
import feign.FeignException;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.naming.ServiceUnavailableException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final UserServiceClient userServiceClient;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        UserCredentialDto userByEmail;

        try {
            userByEmail = userServiceClient.getUserByEmail(loginRequestDto.getEmail());

        } catch (FeignException.NotFound e) {
            throw new BadCredentialsException("Invalid email or password");
        }catch (RetryableException | FeignException.ServiceUnavailable e){
            throw new ServiceNotAvailable("Service Not available");
        }

        if (!passwordEncoder.matches(
                loginRequestDto.getPassword(),
                userByEmail.getPasswordHash())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        String accessToken = jwtService.generateToken(userByEmail);

        LoginResponseDto response = new LoginResponseDto();
        response.setToken(accessToken);
        response.setUserId(userByEmail.getUserId());

        return response;
    }
}
