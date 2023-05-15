package com.example.Web.service.order;

import com.example.Web.dto.order.CheckoutInputDto;
import com.example.Web.dto.order.OrderInputDto;
import com.example.Web.dto.order.OrderOutputDto;
import com.example.Web.model.User;

public interface OrderService {
	
	OrderOutputDto addToCart(OrderInputDto orderInputDto, String token);
	
	OrderOutputDto getCart(User user);
	
	OrderOutputDto checkout(User user, CheckoutInputDto checkoutInput);
}
