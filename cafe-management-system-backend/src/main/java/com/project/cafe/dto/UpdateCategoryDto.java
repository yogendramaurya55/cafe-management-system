package com.project.cafe.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCategoryDto {

	@NotBlank(message = "feild is required")
	private String id;
	
	@NotBlank(message = "field is required")
	private String name;
}
