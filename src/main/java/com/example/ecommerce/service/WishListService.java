package com.example.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.model.User;
import com.example.ecommerce.model.WishList;
import com.example.ecommerce.repository.WishListRepository;

@Service
public class WishListService {
	
	@Autowired
	private WishListRepository wishListRepository;
	
	@Autowired
	private ProductService productService;
	
	public void createWishList(WishList wishlist) {
		wishListRepository.save(wishlist);
	}
	
	public List<ProductDTO> getWishListForUser(User user){
		List<WishList> wishlistItems =  wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		for(WishList item : wishlistItems) {
			productDTOs.add(productService.getProductDTO(item.getProduct()));
		}
		
		return productDTOs;
	}

}
