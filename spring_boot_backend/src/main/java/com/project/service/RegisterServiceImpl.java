package com.project.service;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.Dao.RegisterDao;
import com.project.DTO.ApiResponse;
import com.project.DTO.registerDto;
import com.project.entities.Register;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Service
@Transactional

public class RegisterServiceImpl implements RegisterService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);
	
	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	@Autowired
	RegisterDao registerDao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ApiResponse signUp(registerDto register) {
		
		String msg="registered successfully";
		try {
			
		
		Register registered =mapper.map(register,Register.class);
		registered.setPassword(PASSWORD_ENCODER.encode(registered.getPassword()));
		registerDao.save(registered);
		return new ApiResponse(msg);
		}
		catch (Exception e) {
            logger.error("Registration failed", e);
            return new ApiResponse("Registration failed: " + e.getMessage());
        }

	}
	
}
