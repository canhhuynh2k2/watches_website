package com.example.Web.dto.contact;

import lombok.*;

@Data
@AllArgsConstructor
public class ContactInputDto {
	
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String phoneNumber;
	
	private String subjectName;
	
	private String content;
}
