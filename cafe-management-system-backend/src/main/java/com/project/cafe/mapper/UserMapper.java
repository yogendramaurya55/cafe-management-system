package com.project.cafe.mapper;

import com.project.cafe.dto.UserDto;
import com.project.cafe.entity.User;

public class UserMapper {
	
	public static User getUserEntity(UserDto reqDto) {
		return User.builder()
				.name(reqDto.getName())
				.email(reqDto.getEmail())
				.contactNumber(reqDto.getContactNumber())
				.password(reqDto.getPassword())
				.role(reqDto.getRole())
				.status(reqDto.getStatus())
				.build();
	}
	
	public static UserDto getUserDto(User user) {
		return UserDto.builder()
				.id(user.getId())
				.name(user.getName())
				.email(user.getEmail())
				.contactNumber(user.getContactNumber())
				.role(user.getRole())
				.status(user.getStatus())
				.build();
	}

}
