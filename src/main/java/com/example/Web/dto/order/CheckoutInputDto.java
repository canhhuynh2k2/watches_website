package com.example.Web.dto.order;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutInputDto {
	
	private String fullname;
	private String email;
	private String phoneNumber;
	private String address;
	private String note;
}
