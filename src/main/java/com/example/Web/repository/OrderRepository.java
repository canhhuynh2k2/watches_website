package com.example.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Web.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
