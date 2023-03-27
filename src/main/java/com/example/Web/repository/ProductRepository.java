package com.example.Web.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Web.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	public Product getByCode(String title);
	
}
