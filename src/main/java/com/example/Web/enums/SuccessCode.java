package com.example.Web.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum SuccessCode {
	CREATE_PRODUCT_SUCCESS(true, "Thêm sản phẩm thành công!"),
	UPDATE_PRODUCT_SUCCESS(true, "Cập nhật sản phẩm thành công!"),
	DELETE_PRODUCT_SUCCESS(true, "Xóa sản phẩm thành công!"),
	
	ADD_PRODUCT_DETAIL_SUCCESS(true, "Thêm chi tiết sản phẩm thành công!"),
	UPDATE_PRODUCT_DETAIL_SUCCESS(true, "Cập nhật chi tiết sản phẩm thành công!"),
	
	CREATE_CATEGORY_SUCCESS(true, "Thêm danh mục thành công!"),
	UPDATE_CATEGORY_SUCCESS(true, "Cập nhật danh mục thành công!"),
	DELETE_CATEGORY_SUCCESS(true, "Xóa danh mục thành công!"),
	
	USER_CREATED(true, "Tạo tài khoản thành công!"), 
	
	ADD_TO_CART_SUCCESS(true, "Thêm sản phẩm vào giỏ hàng thành công!"),
	
	CONTACT_SUCCESS(true, "Liên hệ thành công, chúng tôi sẽ phản hồi lại sớm nhất có thể!"),
	DELETE_CART_ITEM_SUCCESS(true, "Xóa sản phẩm khỏi giỏ hàng thành công"),
	UPDATE_CART_ITEM_QUANTITY_SUCCESS(true, "Cập nhật số lượng sản phẩm trong giỏ hàng thành công");
	
	private Boolean success;
	private String message;
}
