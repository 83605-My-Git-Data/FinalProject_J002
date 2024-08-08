package com.project.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.project.dto.ApiResponse;
import com.project.dto.signInDto;

public interface LogInService extends UserDetailsService{
	ApiResponse signIn(signInDto signInDto);

	
}
