package com.aditya_ecommerce.repository;

import com.aditya_ecommerce.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<RefreshToken,UUID > {
}
