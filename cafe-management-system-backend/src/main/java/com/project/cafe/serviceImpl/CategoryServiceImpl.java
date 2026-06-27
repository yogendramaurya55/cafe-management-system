package com.project.cafe.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.cafe.JWT.JwtFilter;
import com.project.cafe.constants.CafeContants;
import com.project.cafe.dto.CategoryDto;
import com.project.cafe.dto.UpdateCategoryDto;
import com.project.cafe.entity.Category;
import com.project.cafe.mapper.CategoryMapper;
import com.project.cafe.repository.CategoryRepository;
import com.project.cafe.service.CategoryService;
import com.project.cafe.utils.CafeUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepo;
	private final JwtFilter jwtFilter;

	@Override
	public ResponseEntity<String> addNewCategory(CategoryDto reqDto) {

		if (jwtFilter.isAdmin()) {

			Category category = Category.builder().name(reqDto.getName()).build();

			categoryRepo.save(category);

			return CafeUtils.getResponseEntity("Category added successfully", HttpStatus.OK);
		}

		return CafeUtils.getResponseEntity(CafeContants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
	}

	@Override
	public ResponseEntity<List<CategoryDto>> getAllCategory(String filterValue) {

		List<Category> categories = new ArrayList<>();
		if (jwtFilter.isAdmin()) {
			categories = categoryRepo.findAll();
		}

		// if the user is logged in then we need to send the categories which have at
		// least 1 product in it
		categories = categoryRepo.getAllCategory();
		List<CategoryDto> res = categories.stream().map(CategoryMapper::getResponseDto).toList();

		return CafeUtils.getResponseEntity(res, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<String> updateCategory(UpdateCategoryDto reqDto) throws NumberFormatException, Exception {
		
		if (jwtFilter.isAdmin()) {
			Category category = categoryRepo.findById(Integer.parseInt(reqDto.getId())).orElseThrow(
					() -> new Exception("no category found with this id")
					);
			
			category.setName(reqDto.getName());
			
			categoryRepo.save(category);
			
			return CafeUtils.getResponseEntity("category successfully updated", HttpStatus.OK);
		}
		
		return CafeUtils.getResponseEntity(CafeContants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
		
		
	}

}
