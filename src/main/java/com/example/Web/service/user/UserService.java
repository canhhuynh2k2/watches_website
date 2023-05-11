package com.example.Web.service.user;

import com.example.Web.dto.user.SignInDto;
import com.example.Web.dto.user.SignInResponseDto;
import com.example.Web.dto.user.SignUpDto;
import com.example.Web.dto.user.UserOutputDto;

public interface UserService {
	
	void signUp(SignUpDto signUpDto);
	
	SignInResponseDto signIn(SignInDto signInDto);
	
	UserOutputDto getUser(String token);
}
