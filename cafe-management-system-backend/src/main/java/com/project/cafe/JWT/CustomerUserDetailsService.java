package com.project.cafe.JWT;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.cafe.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

	private final UserRepository userRepo;

	private com.project.cafe.entity.User userDetails;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		userDetails = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("no user exists"));

		return new User(userDetails.getEmail(), userDetails.getPassword(), new ArrayList<>());
	}

	public com.project.cafe.entity.User getUserDetail() {
		return userDetails;
	}

}
