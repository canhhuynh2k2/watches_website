package com.example.Web.dto.product;

import com.example.Web.model.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInputDto {
	@NotBlank(message = "Trường \"Tên sản phẩm\" không được trống")
	private String title;
	
	@NotNull(message = "Trường \"giá gốc\" không được trống")
	private Long price;
	
	@NotNull(message = "Trường \"giá sale\" không được trống")
	private Long discount;
	
	@NotNull
	private String thumbnail;
	private String description;
	
	@NotNull(message = "Trường \"danh mục\" không được trống")
	private Long categoryId;
}
