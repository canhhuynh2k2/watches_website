package com.example.Web.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
public class RoleInputDto {
	
	@NotBlank
	private String name;
}
