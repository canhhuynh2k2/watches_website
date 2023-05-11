package com.example.Web.service.role;

import java.util.List;

import com.example.Web.dto.role.RoleInputDto;
import com.example.Web.dto.role.RoleOutputDto;
import com.example.Web.model.Role;

public interface RoleService {
	
	RoleOutputDto readRole(Integer id);
	
	List<RoleOutputDto> getRoles(Integer id);
	
	void addRole(RoleInputDto roleInput);
	
	Role getEntityFromInput(RoleInputDto roleInput);
}
