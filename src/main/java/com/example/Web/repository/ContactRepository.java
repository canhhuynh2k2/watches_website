package com.example.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Web.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

}
