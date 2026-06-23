package com.project.cafe.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.cafe.JWT.CustomerUserDetailsService;
import com.project.cafe.JWT.JwtFilter;
import com.project.cafe.JWT.JwtUtils;
import com.project.cafe.constants.CafeContants;
import com.project.cafe.dto.LoginDto;
import com.project.cafe.dto.UserDto;
import com.project.cafe.entity.User;
import com.project.cafe.enums.Role;
import com.project.cafe.enums.Status;
import com.project.cafe.exception.ResourceNotFound;
import com.project.cafe.mapper.UserMapper;
import com.project.cafe.repository.UserRepository;
import com.project.cafe.service.UserService;
import com.project.cafe.utils.CafeUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final JwtFilter jwtFilter;
	private final UserRepository userRepo;
	private final AuthenticationManager authManager;
	private final CustomerUserDetailsService customerUserDetailsService;
	private final JwtUtils jwtUtil;
	private final PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<String> signUp(UserDto reqDto) throws Exception {
		log.info("inside signup", reqDto);

		if (userRepo.existsByEmail(reqDto.getEmail())) {
			throw new IllegalArgumentException("Email Already Exists.");
		}

		reqDto.setPassword(passwordEncoder.encode(reqDto.getPassword()));

		User user = userRepo.save(UserMapper.getUserEntity(reqDto));

		return CafeUtils.getResponseEntity("Successfully Registered", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> login(LoginDto reqDto) throws Exception {

		Authentication auth = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(reqDto.getEmail(), reqDto.getPassword()));
		if (auth.isAuthenticated()) {
			if (customerUserDetailsService.getUserDetail().getStatus() == Status.TRUE) {
				log.info("user is authenticated and moving to generate jwt token");
				return CafeUtils
						.getResponseEntity(
								"{\"token\":\""
										+ jwtUtil.generateToken(customerUserDetailsService.getUserDetail().getEmail(),
												customerUserDetailsService.getUserDetail().getRole())
										+ "\"}",
								HttpStatus.OK);
			} else {
				return CafeUtils.getResponseEntity("{\"message\":\"" + "Wait for admin approval" + "\"}",
						HttpStatus.BAD_REQUEST);
			}
		}
		return CafeUtils.getResponseEntity(CafeContants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
	}

	@Override
	public ResponseEntity<List<UserDto>> getAllUsers() {

		if (jwtFilter.isAdmin()) {
			List<User> dbUsers = userRepo.findAll();

			List<UserDto> users = dbUsers.stream().map(UserMapper::getUserDto).toList();

			return CafeUtils.getResponseEntity(users, HttpStatus.OK);
		} else {
			return CafeUtils.getResponseEntity(new ArrayList<>(), HttpStatus.UNAUTHORIZED);

		}

	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		if (jwtFilter.isAdmin()) {
			Status status = null;

			if (requestMap.get("status").equalsIgnoreCase("true")) {
				status = Status.TRUE;
			} else if (requestMap.get("status").equalsIgnoreCase("false")) {
				status = Status.FALSE;
			} else {
				throw new IllegalArgumentException("enter a valid status");
			}
			Integer rowsAffected = userRepo.updateStatus(status, requestMap.get("id"));
			
			if(rowsAffected == 0) {
				throw new ResourceNotFound("No user exists");
			}

			return CafeUtils.getResponseEntity("Successfully updated status", HttpStatus.OK);

		} else {
			return CafeUtils.getResponseEntity(CafeContants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
		}
	}

}
