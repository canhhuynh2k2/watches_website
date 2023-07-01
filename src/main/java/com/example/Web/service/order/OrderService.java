package com.example.Web.service.order;

import java.util.List;

import com.example.Web.dto.order.CheckoutInputDto;
import com.example.Web.dto.order.OrderInputDto;
import com.example.Web.dto.order.OrderOutputDto;
import com.example.Web.model.User;

public interface OrderService {
	
	OrderOutputDto addToCart(OrderInputDto orderInputDto, String token);
	
	OrderOutputDto getCart(User user);
	
	OrderOutputDto checkout(User user, CheckoutInputDto checkoutInput);
	
	void updateCartItemQuantity(Long cartId, Long itemId, int quantity, String token);

	void deleteItem(Long cartId, Long itemId, String token);
	
	List<OrderOutputDto> getAllOrdersByUser(Long userId);
	
	OrderOutputDto getOrder(Long userId, Long orderId);
	
	List<OrderOutputDto> readAll();
	
	void updateStatus(Long orderId, int status);
}
