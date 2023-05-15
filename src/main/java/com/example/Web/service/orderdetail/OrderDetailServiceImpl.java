package com.example.Web.service.orderdetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Web.dto.order.OrderInputDto;
import com.example.Web.dto.orderdetail.OrderDetailOutputDto;
import com.example.Web.enums.ErrorCode;
import com.example.Web.exceptions.CommandException;
import com.example.Web.model.Order;
import com.example.Web.model.OrderDetail;
import com.example.Web.model.Product;
import com.example.Web.repository.OrderDetailRepository;
import com.example.Web.service.product.ProductServiceImpl;
import com.example.Web.utils.Helper;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	@Autowired
	private OrderDetailMapper mapper;
	
	@Autowired
	private ProductServiceImpl productService;
	
	@Override
	public void add(Order cart, OrderInputDto orderInputDto) {
		Product product = productService.getProduct(orderInputDto.getProductId());
		OrderDetail orderItem = new OrderDetail();
		orderItem.setPrice(product.getPrice());
		if(product.getQuantity() < orderInputDto.getQuantity()) {
			throw new CommandException(ErrorCode.QUANTITY_OF_PRODUCT_LEFT_NOT_ENOUGH);
		}
		orderItem.setQuantity(orderInputDto.getQuantity());
		orderItem.setDiscount(product.getDiscount());
		orderItem.setOrder(cart);
		orderItem.setProduct(product);
		orderDetailRepo.save(orderItem);
		
	}
	
	@Override
	public void update(Order cart, OrderInputDto orderInputDto) {
		System.out.println(cart.getId() + " " + orderInputDto.getProductId());
		OrderDetail orderDetail = orderDetailRepo.getOrderDetail(orderInputDto.getProductId(), cart.getId());
		if(Helper.notNull(orderDetail)) {
			if(orderInputDto.getQuantity()
					> productService.getProduct(orderInputDto.getProductId()).getQuantity()) {
				throw new CommandException(ErrorCode.QUANTITY_OF_PRODUCT_LEFT_NOT_ENOUGH);
			}
			orderDetail.setQuantity(orderInputDto.getQuantity());
			orderDetailRepo.save(orderDetail);
		}
		else {
			add(cart, orderInputDto);
		}
		
		
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
	public List<OrderDetailOutputDto> getAll(Long id) {
		return orderDetailRepo.getAllByOrderId(id).stream()
				.map(entity -> getOutputFromEntity(entity)).collect(Collectors.toList());
	}
	
	@Override
	public OrderDetailOutputDto getOutputFromEntity(OrderDetail orderDetail) {
		OrderDetailOutputDto orderDetailOutput = mapper.getOutputFromEntity(orderDetail);
		orderDetailOutput.setProductOutputDto(productService.getPublishedProduct(orderDetail.getProduct().getId()));
		return orderDetailOutput;
	}

	@Override
	public void updateQuantityOfProducts(List<OrderDetailOutputDto> listItems) {
		for(OrderDetailOutputDto orderItem : listItems) {
			productService.updateQuantity(orderItem.getProductOutputDto().getId(), orderItem.getQuantity());
		}
		
		
	}
	
}
