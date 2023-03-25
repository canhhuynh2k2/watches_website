package com.example.Web.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum SuccessCode {
	CREATE_PRODUCT_SUCCESS(true, "Thêm sản phẩm thành công!"),
	UPDATE_PRODUCT_SUCCESS(true, "Cập nhật sản phẩm thành công!"),
	DELETE_PRODUCT_SUCCESS(true, "Xóa sản phẩm thành công!"),
	
	CREATE_CATEGORY_SUCCESS(true, "Thêm danh mục thành công!"),
	UPDATE_CATEGORY_SUCCESS(true, "Cập nhật danh mục thành công!"),
	DELETE_CATEGORY_SUCCESS(true, "Xóa danh mục thành công!");;
	
	private Boolean success;
	private String message;
}
