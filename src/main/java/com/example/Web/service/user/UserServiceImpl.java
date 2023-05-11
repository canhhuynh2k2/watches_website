package com.example.Web.service.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Web.dto.user.SignInDto;
import com.example.Web.dto.user.SignInResponseDto;
import com.example.Web.dto.user.SignUpDto;
import com.example.Web.dto.user.UserOutputDto;
import com.example.Web.enums.ErrorCode;
import com.example.Web.exceptions.CommandException;
import com.example.Web.model.AuthenticationToken;
import com.example.Web.model.User;
import com.example.Web.repository.UserRepository;
import com.example.Web.service.authenticationService.AuthenticationServiceImpl;
import com.example.Web.service.role.RoleServiceImpl;
import com.example.Web.utils.Helper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserMapper mapper;
	
	@Autowired
	AuthenticationServiceImpl authenticationService;
	
	@Autowired
	RoleServiceImpl roleService;
	
	@Override
	@Transactional
	public void signUp(SignUpDto signUpDto) {
		if(Helper.notNull(userRepo.findByEmail(signUpDto.getEmail()))) {
			throw new CommandException(ErrorCode.USER_IS_EXISTS);
		}
		
		signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		User user = mapper.getEntityFromSignUpInput(signUpDto);
		
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		
		try {
			User createdUser = userRepo.save(user);
			final AuthenticationToken authenticationToken = new AuthenticationToken(createdUser);
			authenticationService.saveConfirmationToken(authenticationToken);
		}catch(Exception e) {
			throw new CommandException(ErrorCode.CREATE_USER_FAIL);
		}
	}
	
	
	public SignInResponseDto signIn(SignInDto signInDto) {
		User user = userRepo.findByEmail(signInDto.getEmail());
        if(!Helper.notNull(user)){
            throw  new CommandException(ErrorCode.USER_IS_NOT_EXISTS);
        }
        if(!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
        	throw new CommandException(ErrorCode.WRONG_PASSWORD);
        }
        AuthenticationToken token = authenticationService.getToken(user);

        if(!Helper.notNull(token)) {
            throw new CommandException(ErrorCode.AUTH_TOKEN_NOT_PRESENT);
        }
        return new SignInResponseDto(true, token.getToken());
	}
	
	public UserOutputDto getUser(String token) {
        return mapper.getOutputFromEntity(authenticationService.authenticate(token));
	}
	
}
