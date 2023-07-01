package com.example.Web.service.contact;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.Web.dto.contact.ContactInputDto;
import com.example.Web.dto.contact.ContactOutputDto;

import com.example.Web.model.Contact;

@Mapper(componentModel = "spring")
public interface ContactMapper {
	ContactOutputDto getOutputFromEntity(Contact contact);
	
	Contact getEntityFromInput(ContactInputDto contactInput);
	
	void updateEntityFromInput(@MappingTarget Contact entity, ContactInputDto inputDto);
}
