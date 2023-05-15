package com.example.Web.dto.order;

import com.example.Web.model.User;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInputDto {
	
	private Long productId;
	private Integer quantity;
	
}
