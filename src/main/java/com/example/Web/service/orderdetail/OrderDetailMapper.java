package com.example.Web.service.orderdetail;

import org.mapstruct.MappingTarget;

import com.example.Web.dto.orderdetail.OrderDetailInputDto;
import com.example.Web.dto.orderdetail.OrderDetailOutputDto;

import com.example.Web.model.OrderDetail;

public interface OrderDetailMapper {
	
	OrderDetailOutputDto getOutputFromEntity(OrderDetail orderDetail);
	
	OrderDetail getEntityFromInput(OrderDetailInputDto orderDetailInput);
	
	void updateEntityFromInput(@MappingTarget OrderDetail entity, OrderDetailInputDto inputDto);
}
