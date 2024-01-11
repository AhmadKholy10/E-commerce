package com.example.ecommerce.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.exception.AuthenticationFailException;
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
	
	public User getUser(String token) {
		AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
		if(Objects.isNull(authenticationToken)) {
			return null;
		}
		return authenticationToken.getUser();
	}
	
	public void authenticate(String token) {
		if(Objects.isNull(token)) {
			throw new AuthenticationFailException("Token is not present");
		}
		
		if(Objects.isNull(getUser(token))) {
			throw new AuthenticationFailException("Token is not valid");
		}
		
	}
}
