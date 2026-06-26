package com.project.cafe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class forgetPasswordDto {

	@Email(message = "enter a valid email")
	@NotBlank(message = "feild cannot be blank")
	private String email;

}
