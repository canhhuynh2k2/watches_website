package com.example.Web.service.orderdetail;

import java.util.List;

import com.example.Web.dto.order.OrderInputDto;
import com.example.Web.dto.orderdetail.OrderDetailOutputDto;
import com.example.Web.model.Order;
import com.example.Web.model.OrderDetail;

public interface OrderDetailService {

	void add(Order order, OrderInputDto orderInputDto);
	
	void update(Order cart, OrderInputDto orderInputDto);
	
	void updateCartItemQuantity(Order cart, Long itemId, int quantity);
	
	void delete(Order cart, Long itemId);
	
	OrderDetailOutputDto getOrderDetail(Long id);
	
	OrderDetail getOrderDetail(Long productId, Long orderId);
	
	OrderDetailOutputDto getOutputFromEntity(OrderDetail orderDetail);
	
	void updateQuantityOfProducts(List<OrderDetailOutputDto> listItems);
	
	List<OrderDetailOutputDto> getAll(Long id);
	
	List<OrderDetailOutputDto> getOrderDetailByOrderId(Long id);
}
