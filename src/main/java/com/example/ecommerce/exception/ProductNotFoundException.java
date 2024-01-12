package com.example.ecommerce.exception;

public class ProductNotFoundException extends IllegalArgumentException{
	public ProductNotFoundException(String msg) {
		super(msg);
	}

}
