package com.project.cafe.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OtpDetailsDto {
	private String otp;
	private LocalDateTime expiryTime;
	private boolean isVerified;

}
