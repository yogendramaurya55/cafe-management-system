package com.project.cafe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.cafe.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByEmail(String email);

	@Query("""
		       SELECT COUNT(u) > 0
		       FROM User u
		       WHERE u.email = :email
		       """)
		boolean existsByEmail(@Param("email") String email);
	
}
