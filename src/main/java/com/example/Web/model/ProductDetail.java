package com.example.Web.model;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productdetail")
public class ProductDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer status;
	
	private String origin;
	
	private String collection;
	
	private String size;
	
	private String style;
	
	private String machineType;

	private String dial;
	
	private String glassMaterial;
	
	private String caseMaterial;
	private String strapMaterial;
	private String shape;
	private String thickness;
	
	private String waterResistance;
	
	private String benzel;
	private String energyStorage;

	private String weight;
	private String feature;
	
	private String domesticWarranty;
	private String internationalWarranty;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
	private Product product;
}
