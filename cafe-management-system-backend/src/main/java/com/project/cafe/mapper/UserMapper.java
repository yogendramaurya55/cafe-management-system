package com.project.cafe.mapper;

import com.project.cafe.dto.UserDto;
import com.project.cafe.entity.User;
import com.project.cafe.enums.Role;
import com.project.cafe.enums.Status;

public class UserMapper {

	public static User getUserEntity(UserDto reqDto) {
		User user = User.builder().name(reqDto.getName()).email(reqDto.getEmail())
				.contactNumber(reqDto.getContactNumber()).password(reqDto.getPassword()).build();

		if (reqDto.getStatus() == null) {
			user.setStatus(Status.FALSE);
		} else {
			user.setStatus(reqDto.getStatus());
		}

		if (reqDto.getRole() == null) {
			user.setRole(Role.USER);
		} else {
			user.setRole(reqDto.getRole());
		}

		return user;

	}

	public static UserDto getUserDto(User user) {
		return UserDto.builder().id(user.getId()).name(user.getName()).email(user.getEmail())
				.contactNumber(user.getContactNumber()).role(user.getRole()).status(user.getStatus()).build();
	}

}
