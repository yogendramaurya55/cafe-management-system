package com.project.cafe.utils;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.cafe.dto.OtpDetailsDto;
import com.project.cafe.enums.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CafeUtils {

	public static <T> ResponseEntity<T> getResponseEntity(T responseBody, HttpStatus httpStatus) {
		return ResponseEntity.status(httpStatus).body(responseBody);
	}

	public static OtpDetailsDto generateOtp() {
		SecureRandom secureRandom = new SecureRandom();

		Integer otp = 100000 + secureRandom.nextInt(900000);

		return OtpDetailsDto.builder().otp(otp.toString()).expiryTime(LocalDateTime.now().plusMinutes(5)).isVerified(false)
				.build();
	}

}
