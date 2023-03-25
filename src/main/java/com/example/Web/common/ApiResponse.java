package com.example.Web.common;

import java.time.LocalDateTime;

import com.example.Web.enums.SuccessCode;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
	private final boolean success;
	private final String message;
	
	public String getTimestamp() {
		return LocalDateTime.now().toString();
	}
	
	public ApiResponse(SuccessCode successCode) {
		this.success = successCode.getSuccess();
		this.message = successCode.getMessage();
	}
}