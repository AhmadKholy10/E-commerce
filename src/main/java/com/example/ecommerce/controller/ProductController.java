package com.example.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.common.ApiResponse;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.service.ProductService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostMapping
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO){
		Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
		
		if(!optionalCategory.isPresent()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category does not exist"),
					HttpStatus.BAD_REQUEST);
		}
		
		productService.create(productDTO, optionalCategory.get());
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product created successfully"),
				HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAllProducts(){
		List<ProductDTO> productDTOs = productService.getAll();
		return new ResponseEntity<List<ProductDTO>>(productDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/edit/{productId}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO){
		Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
		
		if(!optionalCategory.isPresent()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category does not exist"),
					HttpStatus.BAD_REQUEST);
		}
		
		try {
		productService.update(productId, productDTO);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product updated successfully"),
				HttpStatus.OK);
		}
		catch (EntityNotFoundException e) {
			// TODO: handle exception
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, e.getMessage()),
					HttpStatus.NOT_FOUND);
		}
		
	}

}
