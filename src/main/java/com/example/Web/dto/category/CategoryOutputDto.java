package com.example.Web.dto.category;

import lombok.*;
import java.util.List;

import com.example.Web.dto.product.ProductOutputDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryOutputDto {
	private Long id;
    private String name;
    private String description;
//    List<ProductOutputDto> productOutputDtoList;
}
