package com.example.ecommerce.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.DTO.ResponseDTO;
import com.example.ecommerce.DTO.user.SignUpDTO;
import com.example.ecommerce.model.AuthenticationToken;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenService tokenService;
	
//	@Autowired
//	private PasswordEncoder bCryptPasswordEncoder;

	
	@Transactional
	public ResponseDTO signup(SignUpDTO signupDTO) throws Exception{
		//check if the user email exists
		if(Objects.nonNull(userRepository.findByEmail(signupDTO.getEmail()))) {
			throw new Exception("Email aleady exists");
		}
		
		//hash the password
		String hashedPassword = "dummy_password";
		
		//save the new user
		User user = new User(signupDTO.getFirstName(), signupDTO.getLastName(),
				signupDTO.getEmail(), hashedPassword);
		userRepository.save(user);
		
		//Generate the token
		final AuthenticationToken authenticationToken = new AuthenticationToken(user);
		tokenService.saveToken(authenticationToken);
		
		return new ResponseDTO("Success", "User Created");
	}

}
