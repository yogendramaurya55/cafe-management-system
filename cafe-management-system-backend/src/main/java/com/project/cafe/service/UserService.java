package com.project.cafe.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.project.cafe.dto.UserDto;

public interface UserService {

	ResponseEntity<String> signUp(UserDto reqDto) throws Exception;
}
