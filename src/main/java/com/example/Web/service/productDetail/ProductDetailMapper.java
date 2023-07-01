package com.example.Web.service.productDetail;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.Web.dto.productDetail.ProductDetailInputDto;
import com.example.Web.dto.productDetail.ProductDetailOutputDto;
import com.example.Web.model.ProductDetail;


@Mapper(componentModel = "spring")
public interface ProductDetailMapper {
	
	ProductDetail getEntityFromInput(ProductDetailInputDto inputDto);
	
	ProductDetailOutputDto getOutputFromEntity(ProductDetail entity);

    void updateEntityFromInput(@MappingTarget ProductDetail entity, ProductDetailInputDto inputDto);
}
