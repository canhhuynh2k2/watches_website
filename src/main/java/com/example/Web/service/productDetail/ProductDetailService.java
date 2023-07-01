package com.example.Web.service.productDetail;

import com.example.Web.dto.productDetail.ProductDetailInputDto;
import com.example.Web.dto.productDetail.ProductDetailOutputDto;

public interface ProductDetailService {
	
	void addProductDetail(Long id, ProductDetailInputDto inputDto);
	
	void updateProductDetail(Long id, ProductDetailInputDto inputDto);
	
	ProductDetailOutputDto getProductDetail(Long id);
	
	ProductDetailOutputDto readProductDetail(Long product_id);
}
