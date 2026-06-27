package com.project.cafe.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.project.cafe.dto.CategoryDto;
import com.project.cafe.dto.UpdateCategoryDto;

public interface CategoryService {

	ResponseEntity<String> addNewCategory(CategoryDto reqDto);

	ResponseEntity<List<CategoryDto>> getAllCategory(String filterValue);

	ResponseEntity<String> updateCategory(UpdateCategoryDto reqDto) throws NumberFormatException, Exception;

}
