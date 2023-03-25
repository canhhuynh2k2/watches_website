package com.example.Web.service.product;

import java.util.List;

import com.example.Web.dto.product.ProductInputDto;
import com.example.Web.dto.product.ProductOutputDto;
import com.example.Web.model.Product;

public interface ProductService {
	
	void addProduct(ProductInputDto productInputDto);
	
	void updateProduct(Long id, ProductInputDto productInputDto);
	
	void deleteProduct(Long id);
	
	List<ProductOutputDto> getAllProducts();
	
	Product getProduct(Long id);
	
	Product getProduct(String title);
	
	ProductOutputDto readProduct(Long id);
	
	ProductOutputDto getOutputFromEntity(Product entity);
	
}
