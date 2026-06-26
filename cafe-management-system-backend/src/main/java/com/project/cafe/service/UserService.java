package com.project.cafe.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.project.cafe.dto.LoginDto;
import com.project.cafe.dto.ResetPasswordRequest;
import com.project.cafe.dto.UserDto;
import com.project.cafe.dto.VerifyOtpRequest;
import com.project.cafe.dto.forgetPasswordDto;

import jakarta.validation.Valid;

public interface UserService {

	ResponseEntity<String> signUp(UserDto reqDto) throws Exception;
	public ResponseEntity<String> login( LoginDto reqDto) throws Exception;
	public ResponseEntity<List<UserDto>> getAllUsers() throws Exception;
	public ResponseEntity<String> update(Map<String, String> requestMap) throws Exception;
	public ResponseEntity<String> cehckToken() throws Exception;
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) throws Exception;
	public ResponseEntity<String> forgetPassword(forgetPasswordDto reqDto);
	public ResponseEntity<String> verifyOtp(VerifyOtpRequest verifyReq);
	public ResponseEntity<String> resetPassword( ResetPasswordRequest resetPasswordReq);
}
