package com.example.Web.dto.contact;

import java.util.Date;

import lombok.*;

@Data
@AllArgsConstructor
public class ContactOutputDto {
	private Long id;
	
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String phoneNumber;
	
	private String subjectName;
	
	private String content;
	
	private Date contactAt;
}
