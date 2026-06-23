package com.project.cafe.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.project.cafe.dto.LoginDto;
import com.project.cafe.dto.UserDto;

public interface UserService {

	ResponseEntity<String> signUp(UserDto reqDto) throws Exception;
	public ResponseEntity<String> login( LoginDto reqDto) throws Exception;
	public ResponseEntity<List<UserDto>> getAllUsers() throws Exception;
	public ResponseEntity<String> update(Map<String, String> requestMap);
}
