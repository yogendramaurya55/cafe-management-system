package com.project.cafe.JWT;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

	private final CustomerUserDetailsService service;
	private final JwtUtils jwtUtils;

	Claims claims = null;
	String userName = null;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletrequest, HttpServletResponse httpServletresponse,
			FilterChain filterChain) throws ServletException, IOException {
		log.info("Path : {}" ,httpServletrequest.getServletPath());

		String path = httpServletrequest.getServletPath();

		if (path.equals("/user/login")
		        || path.equals("/user/signup")
		        || path.equals("/user/forgotPassword")) {

		    filterChain.doFilter(httpServletrequest, httpServletresponse);
		    return;
		} else {
			String authorizationHeader = httpServletrequest.getHeader("Authorization");
			String token = null;

			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
				token = authorizationHeader.substring(7);
				userName = jwtUtils.extractUsername(token);
				claims = jwtUtils.extractAllClaims(token);
			}

			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = service.loadUserByUsername(userName);
				if (jwtUtils.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken userNamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					userNamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletrequest));
					SecurityContextHolder.getContext().setAuthentication(userNamePasswordAuthenticationToken);
				}
			}

			filterChain.doFilter(httpServletrequest, httpServletresponse);
		}

	}
	
	public boolean isAdmin() {
		return "admin".equalsIgnoreCase((String) claims.get("role"));
	}
	
	public boolean isUser() {
		return "user".equalsIgnoreCase((String) claims.get("role"));
	}
	
	public String getCurrentUser() {
		return userName;
	}

}
