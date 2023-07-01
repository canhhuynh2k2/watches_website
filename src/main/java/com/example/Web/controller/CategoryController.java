package com.example.Web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Web.common.ApiResponse;
import com.example.Web.dto.category.CategoryInputDto;
import com.example.Web.dto.category.CategoryOutputDto;
import com.example.Web.dto.product.ProductOutputDto;
import com.example.Web.enums.SuccessCode;
import com.example.Web.service.category.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/category")

public class CategoryController {
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody @Valid CategoryInputDto categoryInputDto) {
		categoryServiceImpl.createCategory(categoryInputDto);
		return new ResponseEntity<ApiResponse>(new ApiResponse(SuccessCode.CREATE_CATEGORY_SUCCESS), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("id") Long id, @RequestBody @Valid CategoryInputDto categoryInputDto) {
		categoryServiceImpl.updateCategory(id, categoryInputDto);
		return new ResponseEntity<ApiResponse>(new ApiResponse(SuccessCode.UPDATE_CATEGORY_SUCCESS), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") Long id) {
		categoryServiceImpl.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse(SuccessCode.DELETE_CATEGORY_SUCCESS), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/get/{id}")
	public CategoryOutputDto getCategoryById(@PathVariable("id") Long id){
		return categoryServiceImpl.getCategoryById(id);
	}
	
	@GetMapping
	public List<CategoryOutputDto> getAll(){
		return categoryServiceImpl.getAllCategories();
	}
	
}
