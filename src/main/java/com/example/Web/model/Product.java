package com.example.Web.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "products")

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(unique=true)
	private String code;
	
	@NotBlank
	private String title;
	
	@NotNull
	private Long price;
	
	@NotNull
	private Long discount;
	
	@NotNull
	private Integer quantity;
	
	@NotNull
	private Integer gender;
	
	@NotBlank
	private String thumbnail;
	
	private String description;
	
	@NotNull
	private Integer shop;

	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	private Date publishedAt;
	

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private Collection<OrderDetail> listOrderDetails;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private Collection<Gallery> listGalleries;
	
	@JsonIgnore
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private ProductDetail productDetail;
}
