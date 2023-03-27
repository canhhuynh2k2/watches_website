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
	private String thumbnail;
	private String description;

	private Date updatedAt;
	
	private CategoryOutputDto categoryOutputDto;
	
	private Integer status;
	
	private String origin;
	
	private String collection;
	
	private Integer gender;
	
	private String size;
	
	private String style;
	
	private String machineType;

	private String dial;
	
	private String glassMaterial;
	
	private String caseMaterial;
	private String strapMaterial;
	private String shape;
	private String thickness;
	
	private String waterResistance;
	
	private String benzel;
	private String energyStorage;

	private String weight;
	private String feature;
	
	private String domesticWarranty;
	private String internationalWarranty;
}
