package com.example.Web.service.category;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.Web.dto.category.CategoryInputDto;
import com.example.Web.dto.category.CategoryOutputDto;
import com.example.Web.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category getEntityFromInput(CategoryInputDto inputDto);

    CategoryOutputDto getOutputFromEntity(Category entity);

    void updateEntityFromInput(@MappingTarget Category entity, CategoryInputDto inputDto);

}