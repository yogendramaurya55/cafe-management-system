package com.project.cafe.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.dto.LoginDto;
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
	
	@PostMapping("/login")
	public ResponseEntity<String> login(
			@Valid @RequestBody LoginDto reqDto 
			)throws Exception{
		
		return userService.login(reqDto);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<UserDto>> getAllUser() throws Exception{
		return userService.getAllUsers();
	}
	
	@PostMapping(path = "/update")
	public ResponseEntity<String> update(
			@RequestBody Map<String, String> requestMap
			)
	{
		return userService.update(requestMap);
	}

}
