package com.example.Web.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "galleries")
public class Gallery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String thumbnail;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
}
