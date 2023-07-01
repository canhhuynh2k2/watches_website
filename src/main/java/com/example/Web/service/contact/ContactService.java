package com.example.Web.service.contact;

import java.util.List;

import com.example.Web.dto.contact.ContactInputDto;
import com.example.Web.dto.contact.ContactOutputDto;

public interface ContactService {
	void contact(ContactInputDto contactInputDto);
	
	List<ContactOutputDto> readContact();
}
