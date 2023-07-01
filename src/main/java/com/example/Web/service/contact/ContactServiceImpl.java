package com.example.Web.service.contact;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Web.dto.contact.ContactInputDto;
import com.example.Web.dto.contact.ContactOutputDto;
import com.example.Web.model.Contact;
import com.example.Web.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	ContactRepository contactRepo;
	
	@Autowired
	ContactMapper mapper;
	
	@Override
	public void contact(ContactInputDto contactInputDto) {
		Contact contact = mapper.getEntityFromInput(contactInputDto);
		contact.setContactAt(new Date());
		contactRepo.save(contact);
	}

	@Override
	public List<ContactOutputDto> readContact() {
		return contactRepo.findAll().stream()
				.map(entity -> mapper.getOutputFromEntity(entity)).collect(Collectors.toList());
	}
	
}
