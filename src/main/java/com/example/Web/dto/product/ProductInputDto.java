package com.example.Web.dto.product;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInputDto {
	
	@NotBlank
	@UniqueElements
	private String code;
	
	@NotBlank(message = "Trường \"Tên sản phẩm\" không được trống")
	private String title;
	
	@NotNull(message = "Trường \"giá gốc\" không được trống")
	private Long price;
	
	@NotNull(message = "Trường \"giá sale\" không được trống")
	private Long discount;
	
	@NotNull
	private Integer quantity;
	
	@NotNull
	private String thumbnail;
	private String description;
	
	@NotNull
	private Integer status;
	
	@NotNull
	private String origin;
	
	private String collection;
	
	@NotNull
	private Integer gender;
	
	@NotNull
	private String size;
	
	private String style;
	
	@NotBlank
	private String machineType;

	private String dial;
	
	@NotNull
	private String glassMaterial;
	
	private String caseMaterial;
	private String strapMaterial;
	private String shape;
	private String thickness;
	
	@NotBlank
	private String waterResistance;
	
	private String benzel;
	private String energyStorage;

	private String weight;
	private String feature;
	
	@NotBlank
	private String domesticWarranty;
	private String internationalWarranty;
	
	@NotNull(message = "Trường \"danh mục\" không được trống")
	private Long categoryId;
}
