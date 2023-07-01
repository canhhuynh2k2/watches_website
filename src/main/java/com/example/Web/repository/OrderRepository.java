package com.example.Web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Web.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	@Query(value = "SELECT * FROM Orders p WHERE p.status = 0 AND p.user_id = :id LIMIT 1", nativeQuery = true)
	public Order getCart(Long id);
	
	@Query(value = "SELECT o FROM Order o JOIN OrderDetail od ON"
			+ " o.user.id = :userId AND od.order.id = o.id JOIN Product p ON p.id = od.product.id ORDER BY o.id DESC")
	public List<Order> getOrderAndProduct(Long userId);
	
	@Query(value = "SELECT o FROM Order o JOIN OrderDetail od ON"
			+ " o.id = :orderId AND o.user.id = :userId AND od.order.id = o.id JOIN Product p ON p.id = od.product.id ORDER BY o.id DESC")
	public Order getById(Long userId, Long orderId);
	
	@Query(value ="SELECT o FROM Order o ORDER BY o.id DESC")
	public List<Order> getAll();
}
