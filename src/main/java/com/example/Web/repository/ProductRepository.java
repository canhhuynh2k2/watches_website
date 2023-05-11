package com.example.Web.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Web.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Product getByCode(String title);
	
	@Query("SELECT p FROM Product p WHERE p.id = :id")
	Product getById(Long id);
	
	@Query("SELECT p FROM Product p WHERE p.shop = 1 AND p.id = :id")
	Product getPublishedProductById(Long id);
	
	@Query("SELECT p FROM Product p WHERE p.shop = 1")
	List<Product> getAllPublishedProduct();
	
	@Query(value = "SELECT * FROM Products p WHERE p.shop = 1 AND p.category_id = :id", nativeQuery = true)
	List<Product> getProductByCategoryId(Long id);
}
