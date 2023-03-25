package com.example.Web.dto.product;

import java.util.Date;

import com.example.Web.dto.category.CategoryOutputDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductOutputDto {
	private Long id;
	
	private String title;
	private Long price;
	private Long discount;
	private String thumbnail;
	private String description;

	private Date updatedAt;
	
	private CategoryOutputDto categoryOutputDto;
}
