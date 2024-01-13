package com.example.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.DTO.cart.AddToCartDTO;
import com.example.ecommerce.DTO.cart.CartDTO;
import com.example.ecommerce.common.ApiResponse;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.TokenService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private TokenService tokenService;
	
	//Add Cart Item
	@PostMapping
	public ResponseEntity<ApiResponse> addItemToCart(@RequestParam String token,
													 @RequestBody AddToCartDTO addToCartDTO){
		//authenticate the token
		tokenService.authenticate(token);
				
		//find the user
		User user = tokenService.getUser(token);
		
		//Add the item to the user cart
		cartService.addToCart(addToCartDTO, user);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product added to cart successfully"),
											   HttpStatus.CREATED);
	}
	
	//Get all cart items for a user
	@GetMapping("/{token}")
	public ResponseEntity<CartDTO> getCartItems(@PathVariable String token){
		//authenticate the token
		tokenService.authenticate(token);
				
		//find the user
		User user = tokenService.getUser(token);
		
		//Get all cart items 
		CartDTO cartDTO = cartService.getCartDTO(user);
		
		return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.OK);
	}
	
	//Delete a cart item for a user
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
													  @RequestParam("token") String token){
		//authenticate the token
		tokenService.authenticate(token);
				
		//find the user
		User user = tokenService.getUser(token);
		
		//delete the item
		cartService.deleteItem(user, cartItemId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item removed from the cart successfully"),
				   HttpStatus.OK);
	}

}
