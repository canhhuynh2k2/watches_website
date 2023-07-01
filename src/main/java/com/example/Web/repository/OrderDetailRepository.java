package com.example.Web.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Web.model.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
	
	@Query(value = "SELECT * FROM OrderDetail p WHERE p.product_id = :productId AND p.order_id = :orderId LIMIT 1", nativeQuery = true)
	OrderDetail getOrderDetail(Long productId, Long orderId);
	
	@Query(value = "SELECT * FROM OrderDetail p WHERE p.id = :itemId AND p.order_id = :cartId", nativeQuery = true)
	OrderDetail getCartItem(Long itemId, Long cartId);
	
	ArrayList<OrderDetail> getAllByOrderId(Long id);
	
}
