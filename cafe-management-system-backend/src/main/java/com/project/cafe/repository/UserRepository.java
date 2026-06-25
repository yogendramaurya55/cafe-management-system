package com.project.cafe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.cafe.entity.User;
import com.project.cafe.enums.Status;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByEmail(String email);

	@Query("""
		       SELECT COUNT(u) > 0
		       FROM User u
		       WHERE u.email = :email
		       """)
		boolean existsByEmail(@Param("email") String email);
	
	@Transactional
	@Modifying
	@Query("""
			UPDATE User u 
			SET u.status = :status
			WHERE u.id = :id
			"""
			)
	Integer updateStatus(@Param("status") Status status , @Param("id") String id);
	
	@Query("""
			SELECT u.email
			FROM User u 
			WHERE u.role = 'ADMIN'
			""")
	Optional<List<String>> getAllAdmin();
	
}
