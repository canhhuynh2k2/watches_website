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
	CategoryService categoryService;
	
	@Override
	@Transactional
	public void addProduct(ProductInputDto productInputDto) {
		Product product = mapper.getEntityFromInput(productInputDto);
		product.setDeleted(0);

		product.setCreatedAt(new Date());
		product.setUpdatedAt(new Date());
		Category category = categoryRepo.findById(productInputDto.getCategoryId()).get();
		if(Helper.notNull(category)) {
			product.setCategory(category);
			String str = productInputDto.getTitle().trim().replaceAll("\\s+", " ");
			productInputDto.setTitle(str);
			Product prod = productRepo.getByTitleNotDeleted(productInputDto.getTitle());
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
		product.setUpdatedAt(new Date());
		product.setPrice(productInputDto.getPrice());
		product.setDescription(product.getDescription());
		product.setDiscount(productInputDto.getDiscount());
		product.setThumbnail(productInputDto.getThumbnail());

		Category category = categoryRepo.findById(productInputDto.getCategoryId()).get();
		if(Helper.notNull(category)) {
			product.setCategory(category);
			String title = productInputDto.getTitle().trim().replaceAll("\\s+", " ");
			product.setTitle(title);
			Product prod = productRepo.getByTitleNotDeleted(productInputDto.getTitle());
			if(Helper.notNull(prod)) {
				productRepo.save(product);
			}
			else throw new CommandException(ErrorCode.PRODUCT_IS_EXISTS);
			
		}
	}

	@Override
	public void deleteProduct(Long id){
		Product product = getProduct(id);
		product.setDeleted(1);
		productRepo.save(product);
	}
	
	@Override
	public Product getProduct(String title) {
		Product product = productRepo.getByTitleNotDeleted(title);
		if(Helper.notNull(product)) {
			throw new CommandException(ErrorCode.PRODUCT_IS_EXISTS);
		}
		return product;
	}
	
	@Override
	public Product getProduct(Long id) {
		Product product = productRepo.getProductNotDeletedById(id);
		if(Helper.notNull(product)) {
			return product;
		}
		else throw new CommandException(ErrorCode.PRODUCT_IS_NOT_EXISTS);
	}
	
	@Override
	public ProductOutputDto readProduct(Long id) {
		Product product = productRepo.getProductNotDeletedById(id);
		if(Helper.notNull(product)) {
			ProductOutputDto productOutputDto = mapper.getOutputFromEntity(product);
			productOutputDto.setCategoryOutputDto(categoryService.getOutputFromEntity(product.getCategory()));
			return productOutputDto;
		}
		else throw new CommandException(ErrorCode.PRODUCT_IS_NOT_EXISTS);
	}
	@Override
	public List<ProductOutputDto> getAllProducts() {
		return productRepo.findAll().stream()
				.map(entity -> readProduct(entity.getId())).collect(Collectors.toList());
	}

	@Override
    public ProductOutputDto getOutputFromEntity(Product entity) {
        return mapper.getOutputFromEntity(entity);
    }

}
