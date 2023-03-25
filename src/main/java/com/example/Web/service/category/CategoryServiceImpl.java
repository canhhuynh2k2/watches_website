package com.example.Web.service.category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;
	
	@Override
	@Transactional
	public void createCategory(CategoryInputDto categoryInputDto) {
		Category category = modelMapper.map(categoryInputDto, Category.class);
		getCategory(category.getName());
		categoryRepo.save(category);
	}

	@Override
	@Transactional
	public void updateCategory(Long id, CategoryInputDto categoryInputDto) {
		categoryInputDto.getName();
		Category category = getCategory(id);
		category.setName(categoryInputDto.getName());
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
				.map(entity -> modelMapper.map(entity, CategoryOutputDto.class)).collect(Collectors.toList());
	}
}
