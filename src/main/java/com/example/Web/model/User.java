package com.example.Web.model;

import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String email;
	
	private String phoneNumber;
	
	private String address;
	
	@NotBlank
	private String password;
	
	private String avatar;
	private Date createdAt;
	private Date updatedAt;
	private Date lastLogin;
	
	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Collection<Order> listOrders;
	
	@JsonIgnore
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private AuthenticationToken authenticationToken;
}
