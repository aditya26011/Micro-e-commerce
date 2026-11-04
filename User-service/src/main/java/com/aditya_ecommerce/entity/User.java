package com.aditya_ecommerce.entity;

import com.aditya_ecommerce.entity.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String phoneNumber;

    private String address;

    @Column(unique = true,nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;



}
