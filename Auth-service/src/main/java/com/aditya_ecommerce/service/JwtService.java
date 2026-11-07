package com.aditya_ecommerce.service;

import com.aditya_ecommerce.dtos.UserCredentialDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    private SecretKey generateSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

  public String generateToken(UserCredentialDto user){
    return   Jwts.builder().subject(user.getUserId().toString())
              .claim("Roles",user.getRoles().toString())
              .claim("email",user.getEmail())
              .issuedAt(new Date())
              .expiration(new Date(System.currentTimeMillis() + 1000 *60*10))
              .signWith(generateSecretKey())
              .compact();

    }

}
