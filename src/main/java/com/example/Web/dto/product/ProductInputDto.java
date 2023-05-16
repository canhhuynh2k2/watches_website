package com.example.Web.dto.product;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInputDto {
	
	@NotBlank
	@Column(unique=true)
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
	private Integer gender;
	
	@NotNull
	private String thumbnail;
	private String description;
	
	@NotNull
	private Integer shop;
	@NotNull(message = "Trường \"danh mục\" không được trống")
	private Long categoryId;
}
