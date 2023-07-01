package com.example.Web.service.productDetail;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.Web.dto.productDetail.ProductDetailInputDto;
import com.example.Web.dto.productDetail.ProductDetailOutputDto;
import com.example.Web.enums.ErrorCode;
import com.example.Web.exceptions.CommandException;
import com.example.Web.model.Product;
import com.example.Web.model.ProductDetail;
import com.example.Web.repository.ProductDetailRepository;
import com.example.Web.repository.ProductRepository;
import com.example.Web.utils.Helper;

@Service
public class ProductDetailServiceImpl implements ProductDetailService{

	@Autowired
	ProductDetailRepository productDetailRepo;
	
	@Autowired
	ProductDetailMapper mapper;
	
	@Autowired
	ProductRepository productRepo;
	
	@Override
	public void addProductDetail(Long product_id, ProductDetailInputDto inputDto) {
		
		if(Helper.notNull(productDetailRepo.getByProductId(product_id))){
			throw new CommandException(ErrorCode.PRODUCT_DETAIL_IS_EXISTS);
		}
		ProductDetail productDetail = mapper.getEntityFromInput(inputDto);
		productDetail.setProduct(productRepo.findById(product_id).get());
		productDetailRepo.save(productDetail);
		
		Product product = productRepo.findById(product_id).get();
		product.setUpdatedAt(new Date());
		productRepo.save(product);
	}

	@Override
	public void updateProductDetail(Long product_id, ProductDetailInputDto inputDto) {
		ProductDetail productDetail =  productDetailRepo.getByProductId(product_id);
		mapper.updateEntityFromInput(productDetail, inputDto);
		productDetailRepo.save(productDetail);
		
		Product product = productRepo.findById(product_id).get();
		product.setUpdatedAt(new Date());
		productRepo.save(product);
	}
	
	@Override
	public ProductDetailOutputDto getProductDetail(Long product_id) {
		if(Helper.notNull(productRepo.getPublishedProductById(product_id))) {
			ProductDetail productDetail =  productDetailRepo.getByProductId(product_id);
			if(Helper.notNull(productDetail)) {
				return mapper.getOutputFromEntity(productDetail);
			}
		}
		throw new CommandException(ErrorCode.PRODUCT_DETAIL_IS_NOT_EXISTS);
	}
	
	@Override
	public ProductDetailOutputDto readProductDetail(Long product_id) {
		if(Helper.notNull(productRepo.findById(product_id).get())) {
			ProductDetail productDetail =  productDetailRepo.getByProductId(product_id);
			if(Helper.notNull(productDetail)) {
				return mapper.getOutputFromEntity(productDetail);
			}
		}
		throw new CommandException(ErrorCode.PRODUCT_DETAIL_IS_NOT_EXISTS);
	}
}
