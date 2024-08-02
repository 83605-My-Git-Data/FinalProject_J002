package com.project.service;

import com.project.DTO.ApiResponse;
import com.project.DTO.signInDto;

public interface LogInService {
	ApiResponse signIn(signInDto signInDto);

}
