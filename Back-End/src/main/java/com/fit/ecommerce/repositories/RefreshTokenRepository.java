package com.fit.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.ecommerce.entities.RefreshToken;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findAllByUserId(Long userId);
//    void deleteByToken(String token);
//    void deleteAllByUserId(Long userId);
}