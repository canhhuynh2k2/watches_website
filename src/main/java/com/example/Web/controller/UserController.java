package com.example.Web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.Web.common.ApiResponse;
import com.example.Web.dto.user.SignInDto;
import com.example.Web.dto.user.SignInResponseDto;
import com.example.Web.dto.user.SignUpDto;
import com.example.Web.dto.user.UserOutputDto;
import com.example.Web.enums.SuccessCode;
import com.example.Web.service.user.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> signup(@RequestBody @Valid SignUpDto signUpDto){
		userServiceImpl.signUp(signUpDto);
		return new ResponseEntity<ApiResponse>(new ApiResponse(SuccessCode.USER_CREATED), HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public SignInResponseDto signin(@RequestBody @Valid SignInDto signInDto){
		return userServiceImpl.signIn(signInDto);
	}
	@GetMapping("/user")
	public ResponseEntity<UserOutputDto> getUser(@RequestHeader("authorization") String token){
		return new ResponseEntity<UserOutputDto>(userServiceImpl.getUser(token), HttpStatus.ACCEPTED);
	}
}
