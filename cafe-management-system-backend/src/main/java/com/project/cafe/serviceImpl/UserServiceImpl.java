package com.project.cafe.serviceImpl;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.cafe.dto.UserDto;
import com.project.cafe.entity.User;
import com.project.cafe.mapper.UserMapper;
import com.project.cafe.repository.UserRepository;
import com.project.cafe.service.UserService;
import com.project.cafe.utils.CafeUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepo;
	
	@Override
	public ResponseEntity<String> signUp(UserDto reqDto) throws Exception {
		log.info("inside signup" , reqDto);
		
		
		if(userRepo.existsByEmail(reqDto.getEmail())) {
			throw new Exception("Email Already Exists.");
		}
		
		User user = userRepo.save(UserMapper.getUserEntity(reqDto));
		
		
		return CafeUtils.getResponseEntity("Successfully Registered", HttpStatus.CREATED);
	}

	
	
	

}
