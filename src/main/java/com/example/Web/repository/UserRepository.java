package com.example.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Web.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
