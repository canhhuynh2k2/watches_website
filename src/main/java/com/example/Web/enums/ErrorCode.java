package com.example.Web.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	PRODUCT_IS_EXISTS("Sản phẩm đã tồn tại!"),
	PRODUCT_IS_NOT_EXISTS("Sản phẩm không tồn tại!"),
	CATEGORY_IS_EXISTS("Danh mục không tồn tại!");
	private String message;
	
}
