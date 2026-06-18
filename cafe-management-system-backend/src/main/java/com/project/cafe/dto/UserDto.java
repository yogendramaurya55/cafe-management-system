package com.project.cafe.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private Integer id;
	
	@NotBlank(message = "feild cannot blank")
	@NotNull(message = "name is required")
	private String name;
	
	@NotBlank(message = "feild cannot blank")
	@Pattern(regexp = "^[0-9]{10}$")
	@NotNull(message = "contact number is required")
	private String contactNumber;

	@NotBlank(message = "feild cannot blank")
	@NotNull(message = "email is required")
	@Email(message = "enter the correct email")
	private String email;
	
	@NotBlank(message = "feild cannot blank")
	@NotNull(message = "password is required")
	@Size(min = 8 , message = "password must be greater than 8")
	private String password;
	
	
	private String status;
	
	private String role;
	

}
