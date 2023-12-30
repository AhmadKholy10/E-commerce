package com.example.ecommerce.controller;

import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.common.ApiResponse;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
		categoryService.create(category);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Category Created Successfully"),
											   HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<Category> getAllCategories(){
		return categoryService.getAll();
	}
	
	@PostMapping("/{categoryId}/edit")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long categoryId,
													  @RequestBody Category updatedCategory) {
		if(!categoryService.findById(categoryId)) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category does not exist"),
					   HttpStatus.NOT_FOUND);
		}
		categoryService.update(categoryId, updatedCategory);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Category Updated Successfully"),
				   HttpStatus.OK);
	}
	

}
