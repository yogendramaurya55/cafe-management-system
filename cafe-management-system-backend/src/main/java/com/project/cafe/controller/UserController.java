package com.project.cafe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.dto.UserDto;
import com.project.cafe.serviceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

	private final UserServiceImpl userService;

	
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(
			@Valid @RequestBody UserDto reqDto) throws Exception{
		
			return userService.signUp(reqDto);
		
	}

}
