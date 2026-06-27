package com.project.cafe.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.cafe.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	@Query("""
			SELECT c
			FROM Category c
			""")
	List<Category> getAllCategory();

}
