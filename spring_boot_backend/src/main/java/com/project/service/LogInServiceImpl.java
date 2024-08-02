package com.project.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.RegisterDao;
import com.project.dto.ApiResponse;
import com.project.dto.signInDto;



@Service
@Transactional
public class LogInServiceImpl implements LogInService {
	
	
	@Autowired
	RegisterDao registerDao;
	
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ApiResponse signIn(signInDto signInDto) {
		
		
		
		return null;
	}

}
