package com.example.Web.service.role;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.Web.dto.role.RoleInputDto;
import com.example.Web.dto.role.RoleOutputDto;
import com.example.Web.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	
	RoleOutputDto getOutputFromEntity(Role role);
	
	Role getEntityFromInput(RoleInputDto RoleOutput);
	
	void updateEntityFromInput(@MappingTarget Role role, RoleInputDto inputDto);
}
