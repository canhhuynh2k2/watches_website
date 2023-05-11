package com.example.Web.service.authenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Web.enums.ErrorCode;
import com.example.Web.exceptions.CommandException;
import com.example.Web.model.AuthenticationToken;
import com.example.Web.model.User;
import com.example.Web.repository.TokenRepository;
import com.example.Web.utils.Helper;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
    TokenRepository tokenRepo;
	
	
	@Override
	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		tokenRepo.save(authenticationToken);
		
	}

	@Override
	public AuthenticationToken getToken(User user) {
		return tokenRepo.findTokenByUser(user);
	}


	@Override
	public User authenticate(String token) {
		if (!Helper.notNull(token)) {
            throw new CommandException(ErrorCode.AUTH_TOKEN_NOT_PRESENT);
        }
		AuthenticationToken authenticationToken = tokenRepo.findTokenByToken(token);
        if (Helper.notNull(authenticationToken)) {
            if (Helper.notNull(authenticationToken.getUser())) {
                return authenticationToken.getUser();
            }
        }
        throw new CommandException(ErrorCode.USER_IS_NOT_EXISTS);
	}

}
