package com.project.cafe.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
	
	private String id;
	
	@NotBlank(message = " feild cannot be blank")
	@Length(min = 3 , max = 20 , message = "name must be greater than 3 cand smaller than 20 chanracters")
	private String name;

}
