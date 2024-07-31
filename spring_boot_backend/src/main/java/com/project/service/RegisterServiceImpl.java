package com.project.service;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DTO.ApiResponse;
import com.project.DTO.Registerrequest;
import com.project.Dao.RegisterDao;
import com.project.entities.Register;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Service
@Transactional

public class RegisterServiceImpl implements RegisterService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);
	@Autowired
	RegisterDao registerDao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ApiResponse signup(Registerrequest dto) {
		// TODO Auto-generated method stub
		String msg="registered successfully";
		try {
			
		
		Register register=mapper.map(dto,Register.class);
		registerDao.save(register);
		return new ApiResponse(msg);
		}
		catch (Exception e) {
            logger.error("Registration failed", e);
            return new ApiResponse("Registration failed: " + e.getMessage());
        }

	}
	
}
