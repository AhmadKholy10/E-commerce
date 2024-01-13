package com.example.ecommerce.DTO.cart;

import com.example.ecommerce.DTO.ProductDTO;

public class CartItemDTO {
	private ProductDTO productDTO;
	private int quantity;
	
	
	public CartItemDTO() {
		
	}
	
	public CartItemDTO(ProductDTO productDTO, int quantity) {
		this.productDTO = productDTO;
		this.quantity = quantity;
	}

	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	

}
