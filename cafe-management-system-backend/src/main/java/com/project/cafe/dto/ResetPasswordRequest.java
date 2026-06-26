package com.project.cafe.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResetPasswordRequest {

	@Email(message = "enter a valid email")
	@NotBlank(message = "email cannot be null or blank")
	private String email;
	
	@NotBlank(message ="password cannot be null or blank")
	@Length(min = 8 , max = 12 , message = "password must be between 8 to 12 characters")
	private String newPassword;
}
