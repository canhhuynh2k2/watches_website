package com.example.Web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Web.dto.product.ProductInputDto;
import com.example.Web.dto.product.ProductOutputDto;
import com.example.Web.enums.SuccessCode;
import com.example.Web.service.product.ProductServiceImpl;
import com.example.Web.common.ApiResponse;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addProduct(@RequestBody @Valid ProductInputDto productInputDto) {
		 productServiceImpl.addProduct(productInputDto);
		 return new ResponseEntity<ApiResponse>(new ApiResponse(SuccessCode.CREATE_PRODUCT_SUCCESS), HttpStatus.CREATED);
	}
	
	// get product đã published theo id
	@GetMapping("/get/{id}")
	public ProductOutputDto getProduct(@PathVariable("id") Long id) {
		return productServiceImpl.getPublishedProduct(id);
	}
	
	// get tất cả product đã published
	@GetMapping
	public List<ProductOutputDto> getAllPublishedProducts(){
		return productServiceImpl.getAllPublishedProducts();
	}
	
	// get product theo id
	@GetMapping("/read/{id}")
	public ProductOutputDto readProduct(@PathVariable("id") Long id){
		return productServiceImpl.readProduct(id);
	}
	
	@GetMapping("/read/all")
	public List<ProductOutputDto> readAllProducts(){
		return productServiceImpl.readAllProducts();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") Long id, @RequestBody @Valid ProductInputDto productInputDto){
		productServiceImpl.updateProduct(id, productInputDto);
		return new ResponseEntity<ApiResponse>(new ApiResponse(SuccessCode.UPDATE_PRODUCT_SUCCESS), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") Long id){
		productServiceImpl.deleteProduct(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse(SuccessCode.DELETE_PRODUCT_SUCCESS), HttpStatus.ACCEPTED);
	}
	
//	get product theo category id đã published
	@GetMapping("/getbyCategory/{id}")
	public List<ProductOutputDto> getByCategoryId(@PathVariable("id") Long id){
		return productServiceImpl.getProductByCategoryId(id);
	}
	
	@GetMapping("/get/male")
	public List<ProductOutputDto> getByGenderMale(){
		return productServiceImpl.getProductByGender(1);
	}
	
	@GetMapping("/get/female")
	public List<ProductOutputDto> getByGenderFemale(){
		return productServiceImpl.getProductByGender(0);
	}
	
	@GetMapping("/find")
	public List<ProductOutputDto> findProduct(@RequestParam String key){
		return productServiceImpl.searchProduct(key);
	}
	
}
