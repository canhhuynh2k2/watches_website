package com.example.Web.service.order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.Web.dto.order.OrderInputDto;
import com.example.Web.dto.order.OrderOutputDto;
import com.example.Web.model.Order;
import com.example.Web.model.User;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	
	OrderOutputDto getOutputFromEntity(Order order);
	
	Order getEntityFromInput(OrderInputDto orderInput);
	
	void updateEntityFromInput(@MappingTarget Order entity, OrderInputDto inputDto);
	
	@Mapping(target = "id", ignore = true)
	void updateInformationFromUser(@MappingTarget Order entity, User user);
	
}
