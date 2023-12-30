package com.example.ecommerce.common;

import java.time.LocalDateTime;

public class ApiResponse {
	
	private boolean succcess;
	private String message;
	
	
	public ApiResponse(boolean succcess, String message) {
		super();
		this.succcess = succcess;
		this.message = message;
	}


	public boolean isSucccess() {
		return succcess;
	}


	public void setSucccess(boolean succcess) {
		this.succcess = succcess;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getTimestamp() {
		return LocalDateTime.now().toString();
	}

}
