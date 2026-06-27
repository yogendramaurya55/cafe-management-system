package com.project.cafe.mapper;


import com.project.cafe.dto.CategoryDto;
import com.project.cafe.entity.Category;

public class CategoryMapper {
	
	public static CategoryDto getResponseDto(Category entity) {
		return CategoryDto.builder()
				.id(entity.getId().toString())
				.name(entity.getName())
				.build();
	}

}
