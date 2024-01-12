package com.example.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.exception.ProductNotFoundException;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public void create(ProductDTO productDTO, Category category) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setImageUrl(productDTO.getImageUrl());
		product.setPrice(productDTO.getPrice());
		product.setCategory(category);
		
		productRepository.save(product);
	}
	
	public Product findById(Long id) {
		return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found"));
	}
	
	public ProductDTO getProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageUrl(product.getImageUrl());
		productDTO.setPrice(product.getPrice());
		productDTO.setCategoryId(product.getCategory().getId());
		
		return productDTO;
	}
	
	public List<ProductDTO> getAll(){
		List<Product> products = productRepository.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		for(Product product : products) {
			productDTOs.add(getProductDTO(product));
		}
		
		return productDTOs;
	}

	public void update(Long productId, ProductDTO productDTO) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		
		if(!optionalProduct.isPresent()) {
			throw new EntityNotFoundException("Product Not Found");
		}
		
		Product product = optionalProduct.get();
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setImageUrl(productDTO.getImageUrl());
		product.setPrice(productDTO.getPrice());
		
		productRepository.save(product);
		
		
	}

}
