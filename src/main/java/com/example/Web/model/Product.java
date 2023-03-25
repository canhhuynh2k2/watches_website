package com.example.Web.model;

import java.util.Date;
import java.util.Collection;

import jakarta.persistence.*;
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
	
	private String title;
	private Long price;
	private Long discount;
	private String thumbnail;
	private String description;

	private Date createdAt;
	private Date updatedAt;
	private Integer deleted;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private Collection<OrderDetail> listOrderDetails;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private Collection<Gallery> listGalleries;
}
