package com.project.cafe.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.dto.CategoryDto;
import com.project.cafe.dto.UpdateCategoryDto;
import com.project.cafe.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addNewCategory(
			@Valid @RequestBody CategoryDto reqDto
			)
	{
		return categoryService.addNewCategory(reqDto);
	}

	@GetMapping("/get")
	public ResponseEntity<List<CategoryDto>> getAllCategory(@RequestParam(required = false) String filterValue){
		return categoryService.getAllCategory(filterValue);
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> updateCategory(
			@RequestBody UpdateCategoryDto reqDto
			) throws NumberFormatException, Exception
	{
		return categoryService.updateCategory(reqDto);
	}
}
