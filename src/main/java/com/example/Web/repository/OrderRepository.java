package com.example.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Web.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	@Query(value = "SELECT * FROM Orders p WHERE p.status = 0 AND p.user_id = :id LIMIT 1", nativeQuery = true)
	public Order getCart(Long id);
	
}
