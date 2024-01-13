package com.example.ecommerce.exception;

public class ItemNotFoundException extends IllegalArgumentException{
	public ItemNotFoundException(String msg) {
		super(msg);
	}

}
