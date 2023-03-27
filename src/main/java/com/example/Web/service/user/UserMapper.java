package com.example.Web.service.user;

import org.mapstruct.MappingTarget;

import com.example.Web.dto.user.UserInputDto;
import com.example.Web.dto.user.UserOutputDto;
import com.example.Web.model.User;

public interface UserMapper {
	UserOutputDto getOutputFromEntity(User user);
	
	User getEntityFromInput(UserInputDto userInput);
	
	void updateEntityFromInput(@MappingTarget User entity, UserInputDto inputDto);
}
