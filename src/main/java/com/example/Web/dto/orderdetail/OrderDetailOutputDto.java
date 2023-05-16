package com.example.Web.dto.orderdetail;

import com.example.Web.dto.order.OrderOutputDto;
import com.example.Web.dto.product.ProductOutputDto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailOutputDto {
	
	private Long id;
	private ProductOutputDto productOutputDto;
	private Long price;
	private Long discount;
	private Integer quantity;
}
