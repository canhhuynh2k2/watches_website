package com.example.Web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.Web.common.ApiResponse;
import com.example.Web.dto.order.CheckoutInputDto;
import com.example.Web.dto.order.OrderInputDto;
import com.example.Web.dto.order.OrderOutputDto;
import com.example.Web.enums.SuccessCode;
import com.example.Web.model.User;
import com.example.Web.service.authenticationService.AuthenticationServiceImpl;
import com.example.Web.service.order.OrderServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	OrderServiceImpl orderService;
	
	@Autowired
	AuthenticationServiceImpl authenticationServe;
	
	@PostMapping("/cart/add")
	public OrderOutputDto addToCart(@RequestBody @Valid OrderInputDto orderInputDto,
													@RequestHeader("authorization") String token){
		return orderService.addToCart(orderInputDto, token);
	}
	@PostMapping("/cart/{cartid}/deleteitem/{id}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartid") Long cartId, @PathVariable("id") Long itemId,
													@RequestHeader("authorization") String token){
		orderService.deleteItem(cartId, itemId, token);
		return new ResponseEntity<ApiResponse>(new ApiResponse(SuccessCode.DELETE_CART_ITEM_SUCCESS), HttpStatus.ACCEPTED);
	}
	@PutMapping("/cart/{cartid}/updateitem/{id}/{quantity}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartid") Long cartId, @PathVariable("id") Long itemId,
													@PathVariable("quantity") int quantity,
													@RequestHeader("authorization") String token){
		orderService.updateCartItemQuantity(cartId, itemId, quantity, token);
		return new ResponseEntity<ApiResponse>(new ApiResponse(SuccessCode.UPDATE_CART_ITEM_QUANTITY_SUCCESS), HttpStatus.ACCEPTED);
	}
	@GetMapping("/cart/getall")
	public OrderOutputDto getCart(@RequestHeader("authorization") String token){
		User user = authenticationServe.authenticate(token);
		return orderService.getCart(user);
		
	}
	
	@PostMapping("/checkout")
	public OrderOutputDto checkout(@RequestHeader("authorization") String token, 
									@RequestBody @Valid CheckoutInputDto checkoutInput) {
		User user = authenticationServe.authenticate(token);
		return orderService.checkout(user, checkoutInput);
	}
	
	@GetMapping("/order/getall")
	public List<OrderOutputDto> getAllOrderByUser(@RequestHeader("authorization") String token){
		User user = authenticationServe.authenticate(token);
		return orderService.getAllOrdersByUser(user.getId());
	}
	
	@GetMapping("/order/{id}")
	public OrderOutputDto getOrderByUser(@RequestHeader("authorization") String token,
										@PathVariable("id") Long orderId){
		User user = authenticationServe.authenticate(token);
		return orderService.getOrder(user.getId(), orderId);
	}
	
//	tạm
	@PutMapping("/update/orderstatus/{id}/{status}")
	public void updateStatus(@PathVariable("id") Long orderId, @PathVariable("status") int status){
		orderService.updateStatus(orderId, status);
	}
	
	@GetMapping("/orders")
	public List<OrderOutputDto> getAll(){
		return orderService.readAll();
	}
}
