package com.example.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Web.model.ProductDetail;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long>{
	
	@Query(value = "SELECT * FROM ProductDetail p WHERE p.product_id = :id LIMIT 1", nativeQuery = true)
	ProductDetail getByProductId(@Param("id") Long id);
}
