package com.project.cafe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

	@NotBlank(message = "Filed cannot be blank")
	@Email(message = "must have valid email")
	@NotNull(message = "field must not be null")
	private String email;
	
	@NotBlank(message = "Filed cannot be blank")
	@Size(min = 8 , message = "password must be greater than 8")
	@NotNull(message = "field must not be null")
	private String password;
}
