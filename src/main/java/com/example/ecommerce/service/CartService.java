package com.example.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.DTO.cart.AddToCartDTO;
import com.example.ecommerce.DTO.cart.CartDTO;
import com.example.ecommerce.DTO.cart.CartItemDTO;
import com.example.ecommerce.exception.CustomException;
import com.example.ecommerce.exception.ItemNotFoundException;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	ProductService productService;
	
	public void addToCart(AddToCartDTO addToCartDTO, User user) {
		Product product = productService.findById(addToCartDTO.getProductId());
		
		//save the cart item
		CartItem cartItem = new CartItem(user, product, addToCartDTO.getQuantity());
		cartRepository.save(cartItem);
	}

	public CartDTO getCartDTO(User user) {
		List<CartItem> cartItems = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<CartItemDTO> productDTOs = new ArrayList<>();
		for(CartItem item : cartItems) {
			productDTOs.add(new CartItemDTO(productService.getProductDTO(item.getProduct()), item.getQuantity()));
		}
		
		CartDTO cartDTO = new CartDTO(productDTOs);
		return cartDTO;
	}

	public void deleteItem(User user, Long cartItemId) {
		CartItem item = cartRepository.findById(cartItemId)
				.orElseThrow(()-> new ItemNotFoundException("item not found in the cart"));
		if(item.getUser() != user) {
			throw new CustomException("Item does not exist in the user's cart");
		}
		cartRepository.delete(item);
	}

}
