package com.example.Web.service.order;

import com.example.Web.dto.order.OrderInputDto;

public interface OrderService {
	
	void addToCart(OrderInputDto orderInputDto, String token);
}
