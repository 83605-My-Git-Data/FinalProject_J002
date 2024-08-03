package com.project.service;

import com.project.dto.ApiResponse;
import com.project.dto.signInDto;

public interface LogInService {
	ApiResponse signIn(signInDto signInDto);

}
