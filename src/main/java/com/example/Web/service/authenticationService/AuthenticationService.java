package com.example.Web.service.authenticationService;

import com.example.Web.model.AuthenticationToken;
import com.example.Web.model.User;

public interface AuthenticationService {
	
	void saveConfirmationToken(AuthenticationToken authenticationToken);
	
	AuthenticationToken getToken(User user);
	
	 User authenticate(String token);
}
