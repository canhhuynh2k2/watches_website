package com.example.Web.dto.order;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import com.example.Web.dto.orderdetail.OrderDetailOutputDto;
import com.example.Web.model.OrderDetail;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderOutputDto {
	private Long id;
	private String fullname;
	private String email;
	private String phoneNumber;
	private String address;
	private String note;
	private Date orderDate;
	private Integer status;
	private Collection<OrderDetailOutputDto> listOrderDetails;
}
