package com.example.ecommerce.DTO.cart;

import java.util.List;

public class CartDTO {
	private List<CartItemDTO> products;
	private double totalCost;
	
	public CartDTO() {
		
	}
	
	public CartDTO(List<CartItemDTO> products) {
		super();
		this.products = products;
		this.totalCost = calculateTotalCost();
	}
	
	public double calculateTotalCost() {
	  double sum = 0;
	  for(CartItemDTO cartItem : products) {
		  sum += cartItem.getProductDTO().getPrice() * cartItem.getQuantity() ;
	  }
	  return sum;
	}

	public List<CartItemDTO> getProducts() {
		return products;
	}

	public void setProducts(List<CartItemDTO> products) {
		this.products = products;
		this.totalCost = calculateTotalCost();
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
	
}
