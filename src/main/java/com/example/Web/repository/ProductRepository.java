package com.example.Web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Web.model.Product;
public interface ProductRepository extends JpaRepository<Product, Long>{
	public Product getByTitle(String title);
	
	@Query("SELECT u FROM Product u WHERE u.deleted = 0 AND u.title = :title")
	public Product getByTitleNotDeleted(@Param("title") String title);
	
	@Query("SELECT u FROM Product u WHERE u.id = :id AND u.deleted = 0")
	public Product getProductNotDeletedById(@Param("id") Long id);
	
	@Query("SELECT u FROM Product u WHERE u.deleted = 0")
	public List<Product> findAll();
	
}
