package com.aditya_ecommerce.services;


import com.aditya_ecommerce.dtos.SignUpRequestDto;
import com.aditya_ecommerce.dtos.SignUpResponseDto;
import com.aditya_ecommerce.entity.User;
import com.aditya_ecommerce.entity.enums.Roles;
import com.aditya_ecommerce.exceptions.DuplicateEmailException;
import com.aditya_ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpResponseDto register(SignUpRequestDto signUpRequestDto) {
        if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new DuplicateEmailException("User with this email exist");
        }

        User user=new User();

        user.setName(signUpRequestDto.getName());
        user.setAddress(signUpRequestDto.getAddress());
        user.setRole(Roles.USER);
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));

    User SavedUser= userRepository.save(user);

    SignUpResponseDto signUpResponseDto=new SignUpResponseDto();
    signUpResponseDto.setEmail(SavedUser.getEmail());
    signUpResponseDto.setRole(SavedUser.getRole());
    signUpResponseDto.setName(SavedUser.getName());
    signUpResponseDto.setId(SavedUser.getId());

    return signUpResponseDto;
    }
}
