package com.example.Web.service.product;

import java.util.List;

import com.example.Web.dto.product.ProductInputDto;
import com.example.Web.dto.product.ProductOutputDto;
import com.example.Web.model.Product;

public interface ProductService {
	
	void addProduct(ProductInputDto productInputDto);
	
	void updateProduct(Long id, ProductInputDto productInputDto);
	
	void deleteProduct(Long id);
	
	List<ProductOutputDto> getAllPublishedProducts();
	
	Product getProduct(Long id);
	
	Product getProduct(String title);
	
	List<ProductOutputDto> getProductByCategoryId(Long id);
	
	ProductOutputDto getPublishedProduct(Long id);
	
	ProductOutputDto getOutputFromEntity(Product entity);
	
	ProductOutputDto readProduct(Long id);
	
	List<ProductOutputDto> readAllProducts();
	
//	Update quantity of product after checkout
	void updateQuantity(Long productId, int quantityReduced);
	
}
