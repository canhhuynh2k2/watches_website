package com.example.Web.model;

import java.io.Serializable;

import java.util.Date;
import java.util.Collection;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "orders")
public class Order implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	private String fullname;
	private String email;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	private String address;
	private String note;
	@Column(name = "order_date")
	private Date orderDate;
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	private Collection<OrderDetail> listOrderDetails;
}
