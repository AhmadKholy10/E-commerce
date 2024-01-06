package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.model.AuthenticationToken;
import com.example.ecommerce.model.User;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Long>{
	
	AuthenticationToken findByUser(User user);

}
