package com.example.Web.dto.orderdetail;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailInputDto {
	
	private Long orderId;
	private Long productId;
	private Integer quantity;
}
