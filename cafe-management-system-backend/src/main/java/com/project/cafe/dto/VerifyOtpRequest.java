package com.project.cafe.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyOtpRequest {

	@Email(message = "enter a valid email")
	@NotBlank(message = "feild cannot be blank")
	private String email;
	
	@Length(min = 6 , max = 6 , message = "otp must be of 6 digits")
	@NotBlank(message = "otp must not be blank")
	private String otp;
}
