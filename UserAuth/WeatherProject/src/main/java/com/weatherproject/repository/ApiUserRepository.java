package com.weatherproject.repository;

import com.weatherproject.domain.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiUserRepository extends JpaRepository<ApiUser, Long> {
    ApiUser findByUsername(String username);
    Optional<ApiUser> findById(Long id);
}