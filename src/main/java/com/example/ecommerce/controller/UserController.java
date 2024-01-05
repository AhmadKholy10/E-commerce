package com.example.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.DTO.ResponseDTO;
import com.example.ecommerce.DTO.user.SignUpDTO;
import com.example.ecommerce.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseDTO signup(@RequestBody SignUpDTO signupDTO) throws Exception{
		return userService.signup(signupDTO);
	}

}
