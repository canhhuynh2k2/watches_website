package com.example.Web.service.product;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.Web.dto.product.ProductInputDto;
import com.example.Web.dto.product.ProductOutputDto;
import com.example.Web.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	Product getEntityFromInput(ProductInputDto inputDto);
	
	ProductOutputDto getOutputFromEntity(Product entity);

    void updateEntityFromInput(@MappingTarget Product entity, ProductInputDto inputDto);
}
