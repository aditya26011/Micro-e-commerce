package com.aditya_ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity){
        httpSecurity.formLogin(formConfig->formConfig.disable())
                .sessionManagement(sessionConfig->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrConfig->csrConfig.disable())
                .authorizeHttpRequests(requestConfig->
                        requestConfig
                                .requestMatchers(HttpMethod.GET,"/internal/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/users/register")
                                .permitAll()
                                .anyRequest()
                                .authenticated()




                );



        return httpSecurity.build();
    }
}
