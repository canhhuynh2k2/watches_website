package com.example.Web.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	PRODUCT_IS_EXISTS("Sản phẩm đã tồn tại!"),
	PRODUCT_IS_NOT_EXISTS("Sản phẩm không tồn tại!"),
	
	PRODUCT_DETAIL_IS_EXISTS("Chi tiết sản phẩm đã tồn tại!"),
	PRODUCT_DETAIL_IS_NOT_EXISTS("Không có thông tin chi tiết về sản phẩm này."),
	QUANTITY_OF_PRODUCT_LEFT_NOT_ENOUGH("Số lượng sản phẩm còn lại không đủ"),
	
	CATEGORY_IS_EXISTS("Danh mục tồn tại!"),
	
	ROLE_IS_NOT_EXISTS("Vai trò không tồn tại!"),
	
	USER_IS_EXISTS("Tài khoản người dùng đã tồn tại"),
	USER_IS_NOT_EXISTS("Tài khoản không tồn tại"),
	
	AUTH_TOKEN_NOT_PRESENT("Token không tồn tại"),
	AUTH_TOKEN_NOT_VALID("Token không hợp lệ"),
	
	CREATE_USER_FAIL("Tạo tài khoản thất bại"),
	WRONG_PASSWORD("Mật khẩu không chính xác!"),
	
	ORDER_DETAIL_NOT_EXIST("Chi tiết đơn hàng không tồn tại"),
	CART_IS_EMPTY("Giỏ hàng trống, vui lòng thêm sản phẩm vào giỏ hàng để tiến hành thanh toán"),
	
	DELETE_CART_ITEM_FAIL("Xóa vật phẩm giỏ hàng không thành công");
	
	
	private String message;
	
}
