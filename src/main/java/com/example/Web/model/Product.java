package com.example.Web.model;

import java.util.Date;

import org.hibernate.validator.constraints.UniqueElements;

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
	@UniqueElements
	private String code;
	
	@NotBlank
	private String title;
	
	@NotNull
	private Long price;
	
	@NotNull
	private Long discount;
	
	@NotNull
	private Integer quantity;
	
	@NotBlank
	private String thumbnail;
	
	private String description;

	private Date createdAt;
	private Date updatedAt;
	
	@NotNull
	private Integer status;
	
	@NotNull
	private String origin;
	
	private String collection;
	
	@NotNull
	private Integer gender;
	
	@NotNull
	private String size;
	
	private String style;
	
	@NotBlank
	private String machineType;

	private String dial;
	
	@NotNull
	private String glassMaterial;
	
	private String caseMaterial;
	private String strapMaterial;
	private String shape;
	private String thickness;
	
	@NotBlank
	private String waterResistance;
	
	private String benzel;
	private String energyStorage;

	private String weight;
	private String feature;
	
	@NotBlank
	private String domesticWarranty;
	private String internationalWarranty;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private Collection<OrderDetail> listOrderDetails;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private Collection<Gallery> listGalleries;
}
