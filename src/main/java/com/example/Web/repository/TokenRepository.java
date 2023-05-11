package com.example.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Web.model.AuthenticationToken;
import com.example.Web.model.User;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Long>{
	
	AuthenticationToken findTokenByUser(User user);
	
	AuthenticationToken findTokenByToken(String token);
}
