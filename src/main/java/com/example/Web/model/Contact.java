package com.example.Web.model;


import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "contact")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstname;
	
	private String lastname;
	
	@NotBlank
	private String email;
	
	private String phoneNumber;
	
	@NotBlank
	private String subjectName;
	
	@NotBlank
	private String content;
	
	private Date contactAt;
}