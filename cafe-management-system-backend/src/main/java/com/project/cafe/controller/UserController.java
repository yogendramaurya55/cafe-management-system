package com.project.cafe.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.constants.CafeContants;
import com.project.cafe.dto.LoginDto;
import com.project.cafe.dto.OtpDetailsDto;
import com.project.cafe.dto.ResetPasswordRequest;
import com.project.cafe.dto.UserDto;
import com.project.cafe.dto.VerifyOtpRequest;
import com.project.cafe.dto.forgetPasswordDto;
import com.project.cafe.serviceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

	private final UserServiceImpl userService;

	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@Valid @RequestBody UserDto reqDto) throws Exception {

		return userService.signUp(reqDto);

	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginDto reqDto) throws Exception {

		return userService.login(reqDto);
	}

	@GetMapping("/get")
	public ResponseEntity<List<UserDto>> getAllUser() throws Exception {
		return userService.getAllUsers();
	}

	@PostMapping(path = "/update")
	public ResponseEntity<String> update(@RequestBody Map<String, String> requestMap) throws Exception {
		return userService.update(requestMap);
	}

	@GetMapping("/checkToken")
	public ResponseEntity<String> checkToken() throws Exception {

		return userService.cehckToken();

	}

	@PostMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody Map<String, String> requestMap) throws Exception {
		return userService.changePassword(requestMap);
	}

	@PostMapping("/forgetPassword")
	public ResponseEntity<String> forgetPassword(@Valid @RequestBody forgetPasswordDto reqDto) {
		return userService.forgetPassword(reqDto);
	}

	@PostMapping("/verify-otp")
	public ResponseEntity<String> verifyOtp(@Valid @RequestBody VerifyOtpRequest verifyReq) {
		return userService.verifyOtp(verifyReq);
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordReq) {
		return userService.resetPassword(resetPasswordReq);
	}

}
