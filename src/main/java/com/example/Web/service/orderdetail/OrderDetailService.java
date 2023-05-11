package com.example.Web.service.orderdetail;

import java.util.List;

import com.example.Web.dto.orderdetail.OrderDetailOutputDto;
import com.example.Web.model.OrderDetail;

public interface OrderDetailService {

	void add(OrderDetail orderDetail);
	
	OrderDetailOutputDto getOrderDetail(Long id);
	
	OrderDetail getOrderDetail(Long productId, Long orderId);
	
	List<OrderDetailOutputDto> getAll();
}
