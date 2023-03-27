package com.example.Web.model;

import java.sql.Date;
import java.util.Collection;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
	
	@NotBlank
	@UniqueElements
	private String email;
	
	@NotBlank
	@UniqueElements
	private String phoneNumber;
	
	@NotBlank
	private String address;
	
	@NotBlank
	private String password;
	
	private String avatar;
	private Date createdAt;
	private Date updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Collection<Order> listOrders;
}
