package com.gabriel.apiUserPost.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gabriel.apiUserPost.repositories.UserRepository;
import com.gabriel.apiUserPost.services.TokenService;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
    @Autowired
	private TokenService tokenService;
    
    @Autowired
   	private UserRepository userRepository;
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		var tokenJWT = recoveryToken(request);
		
		if(tokenJWT != null) {
		var subject = tokenService.getSubject(tokenJWT);
		var user = userRepository.findByEmail(subject);
		
		var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		  
		
		filterChain.doFilter(request, response);
	}

	private String recoveryToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		}

		return null;
	}
}