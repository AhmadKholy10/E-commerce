package com.example.ecommerce.DTO.cart;

import java.util.List;

import com.example.ecommerce.DTO.ProductDTO;

public class CartDTO {
	private List<ProductDTO> products;
	private double totalCost;
	
	public CartDTO() {
		
	}
	
	public CartDTO(List<ProductDTO> products) {
		super();
		this.products = products;
		this.totalCost = calculateTotalCost();
	}
	
	public double calculateTotalCost() {
	  return products.stream().mapToDouble(ProductDTO::getPrice).sum();
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
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
