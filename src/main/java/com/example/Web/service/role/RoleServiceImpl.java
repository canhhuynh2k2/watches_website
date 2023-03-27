package com.example.Web.service.role;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Web.dto.role.RoleInputDto;
import com.example.Web.dto.role.RoleOutputDto;
import com.example.Web.enums.ErrorCode;
import com.example.Web.exceptions.CommandException;
import com.example.Web.repository.RoleRepository;
import com.example.Web.utils.Helper;
import com.example.Web.model.Role;
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	RoleMapper mapper;
	
	@Override
	public RoleOutputDto readRole(Integer id) {
		Role role = roleRepo.findById(id).get();
		if(Helper.notNull(role)) {
			return mapper.getOutputFromEntity(role);
		}
		throw new CommandException(ErrorCode.ROLE_IS_NOT_EXISTS);
	}

	@Override
	public List<RoleOutputDto> getRoles(Integer id) {
		return roleRepo.findAll().stream()
				.map(entity -> mapper.getOutputFromEntity(entity)).collect(Collectors.toList());
	}

	@Override
	public void addRole(RoleInputDto roleInput) {
		// TODO Auto-generated method stub
		
	}
}
