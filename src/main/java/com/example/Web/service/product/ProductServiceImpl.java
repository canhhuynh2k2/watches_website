package com.example.Web.service.product;

import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Web.dto.product.ProductInputDto;
import com.example.Web.dto.product.ProductOutputDto;
import com.example.Web.enums.ErrorCode;
import com.example.Web.exceptions.CommandException;
import com.example.Web.model.Category;
import com.example.Web.model.Product;
import com.example.Web.repository.CategoryRepository;
import com.example.Web.repository.ProductDetailRepository;
import com.example.Web.repository.ProductRepository;
import com.example.Web.service.category.CategoryService;
import com.example.Web.utils.Helper;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	ProductMapper mapper;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	ProductDetailRepository productDetailRepo;
	
	@Autowired
	CategoryService categoryService;
	
	@Override
	@Transactional
	public void addProduct(ProductInputDto productInputDto) {
		Product product = mapper.getEntityFromInput(productInputDto);
		product.setCreatedAt(new Date());
		product.setUpdatedAt(new Date());
		if(productInputDto.getShop() == 1) {
			product.setPublishedAt(new Date());
		}
		else {
			product.setPublishedAt(null);
		}
		Category category = categoryRepo.findById(productInputDto.getCategoryId()).get();
		if(Helper.notNull(category)) {
			product.setCategory(category);
			String str = productInputDto.getTitle().trim().replaceAll("\\s+", " ");
			productInputDto.setTitle(str);
			Product prod = productRepo.getByCode(productInputDto.getCode());
			if(Helper.notNull(prod)) {
				throw new CommandException(ErrorCode.PRODUCT_IS_EXISTS);
			}
			productRepo.save(product);
			
		}
		else throw new CommandException(ErrorCode.CATEGORY_IS_EXISTS);
	}

	@Override
	@Transactional
	public void updateProduct(Long id, ProductInputDto productInputDto) {
		Product product = getProduct(id);
		boolean published = false;
		if(product.getShop() == 1) {
			published = true;
		}
		mapper.updateEntityFromInput(product, productInputDto);
		if(product.getShop() == 1 && published == false) {
			product.setPublishedAt(new Date());
		}
		else if(product.getShop() == 0 && published == true) {
			product.setPublishedAt(null);
		}
		product.setUpdatedAt(new Date());
		Category category = categoryRepo.findById(productInputDto.getCategoryId()).get();
		if(Helper.notNull(category)) {
			product.setCategory(category);
			String title = productInputDto.getTitle().trim().replaceAll("\\s+", " ");
			product.setTitle(title);
			Product prod = productRepo.getByCode(productInputDto.getCode());
			if(Helper.notNull(prod)) {
				productRepo.save(product);
				
			}
			else throw new CommandException(ErrorCode.PRODUCT_IS_EXISTS);
			
		}
	}

	@Override
	public void deleteProduct(Long id){
		Product product = getProduct(id);
		if(Helper.notNull(product)) {
			productRepo.delete(product);
		}
		else throw new CommandException(ErrorCode.PRODUCT_IS_NOT_EXISTS);
	}
	
	@Override
	public Product getProduct(String code) {
		Product product = productRepo.getByCode(code);
		if(Helper.notNull(product)) {
			throw new CommandException(ErrorCode.PRODUCT_IS_EXISTS);
		}
		return product;
	}
	
	@Override
	public Product getProduct(Long id) {
		Product product = productRepo.findById(id).get();
		if(Helper.notNull(product)) {
			return product;
		}
		else throw new CommandException(ErrorCode.PRODUCT_IS_NOT_EXISTS);
	}
	
	@Override
	public ProductOutputDto getPublishedProduct(Long id) {
		Product product = productRepo.getPublishedProductById(id);
		if(Helper.notNull(product)) {
			ProductOutputDto productOutputDto = mapper.getOutputFromEntity(product);
			productOutputDto.setCategoryOutputDto(categoryService.getOutputFromEntity(product.getCategory()));
			return productOutputDto;
		}
		else throw new CommandException(ErrorCode.PRODUCT_IS_NOT_EXISTS);
	}

	@Override
    public ProductOutputDto getOutputFromEntity(Product entity) {
        return mapper.getOutputFromEntity(entity);
    }

	@Override
	public List<ProductOutputDto> getAllPublishedProducts() {
		return productRepo.getAllPublishedProduct().stream()
				.map(entity -> mapper.getOutputFromEntity(entity)).collect(Collectors.toList());
	}

	@Override
	public ProductOutputDto readProduct(Long id) {
		Product product = productRepo.getById(id);
		if(Helper.notNull(product)) {
			ProductOutputDto productOutputDto = mapper.getOutputFromEntity(product);
			productOutputDto.setCategoryOutputDto(categoryService.getOutputFromEntity(product.getCategory()));
			return productOutputDto;
		}
		else throw new CommandException(ErrorCode.PRODUCT_IS_NOT_EXISTS);
	}

	@Override
	public List<ProductOutputDto> readAllProducts() {
		return productRepo.findAll().stream()
				.map(entity -> mapper.getOutputFromEntity(entity)).collect(Collectors.toList());
	}

	@Override
	public List<ProductOutputDto> getProductByCategoryId(Long id){
		return productRepo.getProductByCategoryId(id).stream()
				.map(entity -> mapper.getOutputFromEntity(entity)).collect(Collectors.toList());
	}
}
