package com.example.Web.service.orderdetail;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Web.dto.orderdetail.OrderDetailOutputDto;
import com.example.Web.enums.ErrorCode;
import com.example.Web.exceptions.CommandException;
import com.example.Web.model.OrderDetail;
import com.example.Web.repository.OrderDetailRepository;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	@Autowired
	private OrderDetailMapper mapper;
	
	@Override
	public void add(OrderDetail orderDetail) {
		orderDetailRepo.save(orderDetail);
		
	}
	
	@Override
	public OrderDetailOutputDto getOrderDetail(Long id) {
		Optional<OrderDetail> orderDetail = orderDetailRepo.findById(id);
		if(!orderDetail.isPresent()) {
			throw new CommandException(ErrorCode.ORDER_DETAIL_NOT_EXIST);
		}
		return mapper.getOutputFromEntity(orderDetailRepo.findById(id).get());
	}
	
	public OrderDetail getOrderDetail(Long productId, Long orderId) {
		return orderDetailRepo.getOrderDetail(productId, orderId);
	}

	@Override
	public List<OrderDetailOutputDto> getAll() {
		
		return null;
	}
	
}
