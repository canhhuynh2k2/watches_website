package com.example.Web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDto {
	private Long id;
	private String fullname;
	private String email;
	private String phoneNumber;
	private String address;
	private String avatar;
}
