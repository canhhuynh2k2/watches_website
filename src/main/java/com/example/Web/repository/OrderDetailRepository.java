package com.example.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Web.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{

}
