package com.example.Web.service.user;

import org.mapstruct.Mapper;

import com.example.Web.dto.user.SignUpDto;
import com.example.Web.dto.user.UserOutputDto;
import com.example.Web.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	User getEntityFromSignUpInput(SignUpDto signUpInput);
	
	UserOutputDto getOutputFromEntity(User user);
}
