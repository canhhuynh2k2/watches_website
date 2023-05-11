package com.example.Web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Web.dto.orderdetail.OrderDetailOutputDto;
import com.example.Web.service.orderdetail.OrderDetailServiceImpl;

@Controller
@RequestMapping("/api/orderdetail")
public class OrderDetailController {
	
	@Autowired
	OrderDetailServiceImpl orderDetailservice;
	
	@GetMapping("/{id}")
	OrderDetailOutputDto getOrderDetail(@PathVariable("id") Long id) {
		return orderDetailservice.getOrderDetail(id);
	}
	
	@GetMapping
    List<OrderDetailOutputDto> getAll(){
        return orderDetailservice.getAll();
    }
}
