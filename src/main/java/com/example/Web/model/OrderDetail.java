package com.example.Web.model;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "orderdetail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long price;
	private Long discount;
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "orderId")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;

}
