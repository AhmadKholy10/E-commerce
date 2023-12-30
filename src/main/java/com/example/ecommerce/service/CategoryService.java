package com.example.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void create(Category category) {
		categoryRepository.save(category);
	}
	
	public List<Category> getAll(){
		return categoryRepository.findAll();
	}
	
	public void update(Long categoryId, Category updatedCategory) {
		Category category = categoryRepository.getById(categoryId);
		category.setCategoryName(updatedCategory.getCategoryName());
		category.setDescription(updatedCategory.getDescription());
		category.setImageUrl(updatedCategory.getImageUrl());
		categoryRepository.save(category);
		}
	
	public boolean findById(Long categoryId) {
		return categoryRepository.findById(categoryId).isPresent();
	}

}
