package com.example.Web.service.order;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Web.dto.order.CheckoutInputDto;
import com.example.Web.dto.order.OrderInputDto;
import com.example.Web.dto.order.OrderOutputDto;
import com.example.Web.dto.orderdetail.OrderDetailOutputDto;
import com.example.Web.enums.ErrorCode;
import com.example.Web.exceptions.CommandException;
import com.example.Web.model.Order;
import com.example.Web.model.User;
import com.example.Web.repository.OrderRepository;
import com.example.Web.service.authenticationService.AuthenticationServiceImpl;
import com.example.Web.service.orderdetail.OrderDetailServiceImpl;
import com.example.Web.service.product.ProductServiceImpl;
import com.example.Web.utils.Helper;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	OrderDetailServiceImpl orderDetailService;
	
	@Autowired
	ProductServiceImpl productService;
	
	@Autowired
	AuthenticationServiceImpl authenticationService;
	
	@Autowired
	OrderMapper mapper;
	
	@Override
	public OrderOutputDto addToCart(OrderInputDto orderInputDto, String token) {
		User user = authenticationService.authenticate(token);
		Order currOrder = orderRepo.getCart(user.getId());
		if(Helper.notNull(currOrder)) {
			orderDetailService.update(currOrder, orderInputDto);
		}
		else {
			Order newCart = new Order();
			mapper.updateInformationFromUser(newCart, user);
			newCart.setStatus(0);
			newCart.setUser(user);
			orderRepo.save(newCart);
			orderDetailService.add(newCart, orderInputDto);
			
		}
		return getCart(user);
		
	}

	@Override
	public OrderOutputDto getCart(User user) {
		Order currOrder = orderRepo.getCart(user.getId());
		
		if(Helper.notNull(currOrder)) {
			OrderOutputDto orderOutput = mapper.getOutputFromEntity(currOrder);
			List<OrderDetailOutputDto> listItems = orderDetailService.getAll(currOrder.getId());
			orderOutput.setListOrderDetails(listItems);
			return orderOutput;
		}
		return null;
	}
	
	@Override
	public OrderOutputDto checkout(User user, CheckoutInputDto checkoutInput) {
		Order currOrder = orderRepo.getCart(user.getId());
		mapper.updateOrderFromCheckoutInfo(currOrder, checkoutInput);
		currOrder.setStatus(1);
		currOrder.setOrderDate(new Date());
		OrderOutputDto orderOutput = mapper.getOutputFromEntity(currOrder);
		List<OrderDetailOutputDto> listItems = orderDetailService.getAll(currOrder.getId());
		orderDetailService.updateQuantityOfProducts(listItems);
		orderOutput.setListOrderDetails(listItems);
		if(listItems.size() == 0) {
			throw new CommandException(ErrorCode.CART_IS_EMPTY);
		}
		return orderOutput;
	}
}
