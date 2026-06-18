package com.project.cafe.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CafeUtils {
	
	
	
	public static <T> ResponseEntity<T> getResponseEntity(T responseBody , HttpStatus httpStatus){
		return ResponseEntity
				.status(httpStatus)
				.body(responseBody);
	}

}
