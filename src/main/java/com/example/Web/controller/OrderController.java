package com.example.Web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.Web.common.ApiResponse;
import com.example.Web.dto.order.OrderInputDto;
import com.example.Web.enums.SuccessCode;
import com.example.Web.service.order.OrderServiceImpl;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/")
public class OrderController {
	
	@Autowired
	OrderServiceImpl orderService;
	
	@PostMapping("/cart/add")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody @Valid OrderInputDto orderInputDto,
												@RequestParam("token") String token){
		orderService.addToCart(orderInputDto, token);
		return new ResponseEntity<ApiResponse>(new ApiResponse(SuccessCode.ADD_TO_CART_SUCCESS), HttpStatus.ACCEPTED);
	}
}
