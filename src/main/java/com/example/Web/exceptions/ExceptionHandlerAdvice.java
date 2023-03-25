package com.example.Web.exceptions;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.Web.common.ApiResponse;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse> conflict(DataIntegrityViolationException ex){
		String message = getMostSpecificMessage(ex);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, message), HttpStatus.CONFLICT);
	}
	
	// Xử lý mọi exception 
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> unhandledExceptions(Exception ex){
		String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
		
		ex.printStackTrace();
		
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// Xử lý exception của validator
	private String getMostSpecificMessage(DataIntegrityViolationException ex) {
		String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
		
		if(message.contains("Detail:")) {
			message = message.substring(message.indexOf("Detail:")+"Detail:".length());
		}
		
		return message;
	}
}
