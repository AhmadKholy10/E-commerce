package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.model.AuthenticationToken;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Long>{

}
