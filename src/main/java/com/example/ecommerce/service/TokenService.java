package com.example.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.AuthenticationToken;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.TokenRepository;

@Service
public class TokenService {
	
	@Autowired
	private TokenRepository tokenRepository;

	public void saveToken(AuthenticationToken authenticationToken) {
		tokenRepository.save(authenticationToken);
	}
	
	public AuthenticationToken getToken(User user) {
		return tokenRepository.findByUser(user);
	}

}
