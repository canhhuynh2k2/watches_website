package com.example.Web.service.gallery;

import org.mapstruct.MappingTarget;

import com.example.Web.dto.gallery.GalleryInputDto;
import com.example.Web.dto.gallery.GalleryOutputDto;
import com.example.Web.model.Gallery;

public interface GalleryMapper {
	
	GalleryOutputDto getOutputFromEntity(Gallery gallery);
	
	Gallery getEntityFromInput(GalleryInputDto galleryInput);
	
	void updateEntityFromInput(@MappingTarget Gallery entity, GalleryInputDto inputDto);
}
