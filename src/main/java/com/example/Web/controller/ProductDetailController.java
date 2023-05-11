package com.example.Web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Web.common.ApiResponse;
import com.example.Web.dto.productDetail.ProductDetailInputDto;
import com.example.Web.dto.productDetail.ProductDetailOutputDto;
import com.example.Web.enums.SuccessCode;
import com.example.Web.service.productDetail.ProductDetailServiceImpl;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/product/detail")
public class ProductDetailController {
	
	@Autowired
	ProductDetailServiceImpl productDetailServiceImpl;
	
	@PostMapping("/add/{id}")
	public ResponseEntity<ApiResponse> addProductDetail(@PathVariable("id") Long id, @RequestBody @Valid ProductDetailInputDto inputDto){
		productDetailServiceImpl.addProductDetail(id, inputDto);
		return new ResponseEntity<ApiResponse>(new ApiResponse(SuccessCode.ADD_PRODUCT_DETAIL_SUCCESS), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateProductDetail(@PathVariable("id") Long id, @RequestBody @Valid ProductDetailInputDto inputDto){
		productDetailServiceImpl.updateProductDetail(id, inputDto);
		return new ResponseEntity<ApiResponse>(new ApiResponse(SuccessCode.UPDATE_PRODUCT_DETAIL_SUCCESS), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/get/{id}")
	public ProductDetailOutputDto getProductDetail(@PathVariable("id") Long id) {
		return productDetailServiceImpl.getProductDetail(id);
	}
	
	@GetMapping("/read/{id}")
	public ProductDetailOutputDto readProductDetail(@PathVariable("id") Long id) {
		return productDetailServiceImpl.getProductDetail(id);
	}
}
