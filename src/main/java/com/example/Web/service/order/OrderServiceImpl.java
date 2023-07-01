package com.example.Web.service.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Web.dto.order.CheckoutInputDto;
import com.example.Web.dto.order.OrderInputDto;
import com.example.Web.dto.order.OrderOutputDto;
import com.example.Web.dto.orderdetail.OrderDetailOutputDto;
import com.example.Web.enums.ErrorCode;
import com.example.Web.exceptions.CommandException;
import com.example.Web.model.Order;
import com.example.Web.model.OrderDetail;
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
	public void updateCartItemQuantity(Long cartId, Long itemId, int quantity, String token) {
		User user = authenticationService.authenticate(token);
		Order currOrder = orderRepo.getCart(user.getId());
		if(Helper.notNull(currOrder) && cartId == currOrder.getId()) {
			orderDetailService.updateCartItemQuantity(currOrder, itemId, quantity);
		}
	}
	
	@Override
	public void deleteItem(Long cartId, Long itemId, String token) {
		User user = authenticationService.authenticate(token);
		Order currOrder = orderRepo.getCart(user.getId());

		if(Helper.notNull(currOrder) && cartId == currOrder.getId()) {
			orderDetailService.delete(currOrder, itemId);
		}
		else {
			throw new CommandException(ErrorCode.DELETE_CART_ITEM_FAIL);
		}
		
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
	
	@Override
	public List<OrderOutputDto> getAllOrdersByUser(Long userId){
//		return orderRepo.getOrderAndProduct(userId).stream()
//				.map(entity -> mapper.getOutputFromEntity(entity)).collect(Collectors.toList());
		List<Order>  listOrders = orderRepo.getOrderAndProduct(userId);
		List<OrderOutputDto> listOrderDetailOutputDto = listOrders.stream()
				.map(entity -> mapper.getOutputFromEntity(entity)).collect(Collectors.toList());
		for(int i = 0; i < listOrders.size(); i++) {
			List<OrderDetailOutputDto> listDetailOutput = new ArrayList<>();
			for(OrderDetail orderDetail : listOrders.get(i).getListOrderDetails()) {
				listDetailOutput.add(orderDetailService.getOutputFromEntity(orderDetail));
			}
			listOrderDetailOutputDto.get(i).setListOrderDetails(listDetailOutput);
		}
		return listOrderDetailOutputDto;
	}
	
	@Override
	public OrderOutputDto getOrder(Long userId, Long orderId) {
		Order order = orderRepo.getById(userId, orderId);
		OrderOutputDto orderOutputDto = mapper.getOutputFromEntity(order);
		List<OrderDetailOutputDto> listDetailOutput = new ArrayList<>();
		for(OrderDetail orderDetail : order.getListOrderDetails()) {
			listDetailOutput.add(orderDetailService.getOutputFromEntity(orderDetail));

		}
		orderOutputDto.setListOrderDetails(listDetailOutput);
		return orderOutputDto;
	}
	
	@Override
	public List<OrderOutputDto> readAll(){
		List<Order>  listOrders = orderRepo.getAll();
		List<OrderOutputDto> listOrderDetailOutputDto = listOrders.stream()
				.map(entity -> mapper.getOutputFromEntity(entity)).collect(Collectors.toList());
		for(int i = 0; i < listOrders.size(); i++) {
			List<OrderDetailOutputDto> listDetailOutput = new ArrayList<>();
			for(OrderDetail orderDetail : listOrders.get(i).getListOrderDetails()) {
				listDetailOutput.add(orderDetailService.getOutputFromEntity(orderDetail));
			}
			listOrderDetailOutputDto.get(i).setListOrderDetails(listDetailOutput);
		}
		return listOrderDetailOutputDto;
	}
	
	@Override
	public void updateStatus(Long orderId, int status) {
		Order order = orderRepo.findById(orderId).get();
		if(Helper.notNull(order)) {
			order.setStatus(status);
			orderRepo.save(order);
		}
	}
}
