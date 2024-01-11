package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.common.ApiResponse;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.model.WishList;
import com.example.ecommerce.service.TokenService;
import com.example.ecommerce.service.WishListService;

@RestController
@RequestMapping("wishlist")
public class WishListController {
	
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private WishListService wishListService;
	
	@PostMapping
	public ResponseEntity<ApiResponse> addToWishlist(@RequestBody Product product,
													 @RequestParam("token") String token){
		//authenticate the token
		tokenService.authenticate(token);
		
		//find the user
		User user = tokenService.getUser(token);
		
		// save the item to wishlist
		WishList wishlist = new WishList(user, product);
		wishListService.createWishList(wishlist);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to wishlist"),
											    HttpStatus.CREATED);
	}
	
	@GetMapping("/{token}")
	public ResponseEntity<List<ProductDTO>> getWishList(@PathVariable String token){
		//authenticate the token
		tokenService.authenticate(token);
				
		//find the user
		User user = tokenService.getUser(token);
		
		//Get the wishlist items
		List<ProductDTO> productDTOs = wishListService.getWishListForUser(user);
		return new ResponseEntity<List<ProductDTO>>(productDTOs, HttpStatus.OK);
	}

}
