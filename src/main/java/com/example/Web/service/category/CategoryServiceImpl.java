package com.example.Web.service.category;

import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Web.dto.category.CategoryInputDto;
import com.example.Web.dto.category.CategoryOutputDto;

import com.example.Web.enums.ErrorCode;
import com.example.Web.exceptions.CommandException;
import com.example.Web.model.Category;
import com.example.Web.repository.CategoryRepository;
import com.example.Web.utils.Helper;

import jakarta.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
    CategoryMapper mapper;
	
	@Override
	@Transactional
	public void createCategory(CategoryInputDto categoryInputDto) {
		Category category = mapper.getEntityFromInput(categoryInputDto);
		Category cat = categoryRepo.findByName(category.getName());
		if(!Helper.notNull(cat)) {
			category.setCreateAt(new Date());
			category.setUpdateAt(new Date());
			categoryRepo.save(category);
		}
		else throw new CommandException(ErrorCode.CATEGORY_IS_EXISTS);
		
	}

	@Override
	@Transactional
	public void updateCategory(Long id, CategoryInputDto categoryInputDto) {
		categoryInputDto.getName();
		Category category = getCategory(id);
		category.setName(categoryInputDto.getName());
		category.setUpdateAt(new Date());
		category.setDescription(categoryInputDto.getDescription());
		categoryRepo.save(category);
	}

	@Override
	public Category getCategory(String categoryName) {
		Category category = categoryRepo.findByName(categoryName);
		if(Helper.notNull(category)) {
			return category;
		}
		else throw new CommandException(ErrorCode.CATEGORY_IS_EXISTS);
	}
	@Override
	public Category getCategory(Long id) {
		Category category = categoryRepo.findById(id).get();
		if(Helper.notNull(category)) {
			return category;
		}
		else throw new CommandException(ErrorCode.CATEGORY_IS_EXISTS);
	}
	
	@Override
	public void deleteCategory(Long id) {
		categoryRepo.delete(categoryRepo.findById(id).get());
	}

	@Override
	public List<CategoryOutputDto> getAllCategories() {
		return categoryRepo.findAll().stream()
				.map(entity -> mapper.getOutputFromEntity(entity)).collect(Collectors.toList());
	}
	
	@Override
	public CategoryOutputDto getOutputFromEntity(Category categoryEntity) {
		return mapper.getOutputFromEntity(categoryEntity);
	}
	
	@Override
	public CategoryOutputDto getCategoryById(Long id) {
		return mapper.getOutputFromEntity(categoryRepo.getById(id));
	}
	
}
