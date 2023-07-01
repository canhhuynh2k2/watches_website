package com.example.Web.dto.user;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
	
	private String fullname;
	
    private String email;
    
    private String phoneNumber;
    
    private String password;
}
