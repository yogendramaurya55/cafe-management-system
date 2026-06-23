package com.project.cafe.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.cafe.dto.ErrorDto;
import com.project.cafe.utils.CafeUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ErrorDto> exceptionHandler(ResourceNotFound e , HttpServletRequest req){
		ErrorDto res =  new ErrorDto(e.getMessage() , HttpStatus.NOT_FOUND.value() , req.getRequestURL().toString() , LocalDateTime.now());
		
		return CafeUtils.getResponseEntity(res, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorDto> exceptionHandler(IllegalArgumentException e , HttpServletRequest req){
		ErrorDto res = new ErrorDto(e.getMessage() , HttpStatus.BAD_REQUEST.value() , req.getRequestURL().toString() , LocalDateTime.now());
		
		return CafeUtils.getResponseEntity(res, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDto> exceptionHandler(Exception e , HttpServletRequest req){
		ErrorDto res = new ErrorDto(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR.value() , req.getRequestURL().toString() , LocalDateTime.now());
		
		return CafeUtils.getResponseEntity(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
