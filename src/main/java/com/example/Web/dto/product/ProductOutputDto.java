package com.example.Web.dto.product;

import java.util.Date;


import com.example.Web.dto.category.CategoryOutputDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductOutputDto {
	private Long id;
	
	private String code;
	private String title;
	private Long price;
	private Long discount;
	private Integer quantity;
	private Integer gender;
	private String thumbnail;
	private String description;

	private Date updatedAt;
	
	private CategoryOutputDto categoryOutputDto;

}
