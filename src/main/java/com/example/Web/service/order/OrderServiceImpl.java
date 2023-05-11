package com.example.Web.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Web.dto.order.OrderInputDto;
import com.example.Web.enums.ErrorCode;
import com.example.Web.exceptions.CommandException;
import com.example.Web.model.Order;
import com.example.Web.model.OrderDetail;
import com.example.Web.model.Product;
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
	public void addToCart(OrderInputDto orderInputDto, String token) {
		
		User user = authenticationService.authenticate(token);
		Order currOrder = orderRepo.getOrder(user.getId());
		if(Helper.notNull(currOrder)) {
			OrderDetail orderDetail = orderDetailService.getOrderDetail(orderInputDto.getProductId(), currOrder.getId());
			if(Helper.notNull(orderDetail)) {
				if(orderDetail.getQuantity() + orderInputDto.getQuantity()
						> productService.getProduct(orderInputDto.getProductId()).getQuantity()) {
					throw new CommandException(ErrorCode.QUANTITY_OF_PRODUCT_LEFT_NOT_ENOUGH);
				}
				orderDetail.setQuantity(orderDetail.getQuantity() + orderInputDto.getQuantity());
				orderDetailService.add(orderDetail);
			}
			else {
				Product product = productService.getProduct(orderInputDto.getProductId());
				OrderDetail orderItem = new OrderDetail();
				orderItem.setPrice(product.getPrice());
				orderItem.setQuantity(product.getQuantity());
				orderItem.setDiscount(product.getDiscount());
				orderItem.setOrder(currOrder);
				orderItem.setProduct(product);
				orderDetailService.add(orderItem);
			}
		}
		else {
			Order newCart = new Order();
			mapper.updateInformationFromUser(newCart, user);
			newCart.setStatus(0);
			orderRepo.save(newCart);
			Product product = productService.getProduct(orderInputDto.getProductId());
			OrderDetail orderItem = new OrderDetail();
			orderItem.setPrice(product.getPrice());
			orderItem.setQuantity(product.getQuantity());
			orderItem.setDiscount(product.getDiscount());
			orderItem.setOrder(newCart);
			orderItem.setProduct(product);
			orderDetailService.add(orderItem);
			
		}
		
	}
	
//	@Override
//	public void checkout()

}
