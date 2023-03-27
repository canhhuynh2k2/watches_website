package com.example.Web.service.order;

import org.mapstruct.MappingTarget;

import com.example.Web.dto.order.OrderInputDto;
import com.example.Web.dto.order.OrderOutputDto;
import com.example.Web.model.Order;

public interface OrderMapper {
	
	OrderOutputDto getOutputFromEntity(Order order);
	
	Order getEntityFromInput(OrderInputDto orderInput);
	
	void updateEntityFromInput(@MappingTarget Order entity, OrderInputDto inputDto);
}
