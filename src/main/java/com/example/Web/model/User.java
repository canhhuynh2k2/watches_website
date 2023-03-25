package com.example.Web.model;

import java.sql.Date;
import java.util.Collection;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fullname;
	private String email;
	private String phoneNumber;
	private String address;
	private String password;
	private Date createdAt;
	private Date updatedAt;
	private Integer deleted;
	
	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Collection<Order> listOrders;
}
