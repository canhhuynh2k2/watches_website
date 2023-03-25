package com.example.Web.service.category;

import java.util.List;
import java.util.Optional;

import com.example.Web.dto.category.CategoryInputDto;
import com.example.Web.dto.category.CategoryOutputDto;
import com.example.Web.model.Category;

public interface CategoryService {
	public void createCategory(CategoryInputDto categoryInputDto);
	public void updateCategory(Long id, CategoryInputDto categoryInputDto);
	public Category getCategory(String categoryName);
	public Category getCategory(Long id);
	public void deleteCategory(Long id);
	public List<CategoryOutputDto> getAllCategories();
	public CategoryOutputDto getOutputFromEntity(Category categoryEntity);
}
