package com.example.Web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Web.dto.contact.ContactInputDto;
import com.example.Web.dto.contact.ContactOutputDto;
import com.example.Web.enums.SuccessCode;
import com.example.Web.service.contact.ContactServiceImpl;
import com.example.Web.common.ApiResponse;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/contact")
public class ContactController {
	
	@Autowired
	ContactServiceImpl contactService;
	
	@PostMapping
	public ResponseEntity<ApiResponse> contact(@RequestBody @Valid ContactInputDto contactInputDto) {
		contactService.contact(contactInputDto);
		return new ResponseEntity<ApiResponse>(new ApiResponse(SuccessCode.CONTACT_SUCCESS), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/read")
	public List<ContactOutputDto> readContact(){
		return contactService.readContact();
	}
}
