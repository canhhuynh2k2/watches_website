package com.example.Web.dto.user;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {
	
	private String email;
	
	private String password;
}
