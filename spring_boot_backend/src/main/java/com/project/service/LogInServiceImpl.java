package com.project.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.dao.RegisterDao;
import com.project.dto.ApiResponse;
import com.project.dto.signInDto;
import com.project.entities.Register;



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

	  @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        Register register = registerDao.findByEmail(email);
	        if (register == null) {
	            throw new UsernameNotFoundException("User not found with email: " + email);
	        }
	        return new org.springframework.security.core.userdetails.User(register.getEmail(), register.getPassword(), new ArrayList<>());
	    }
	
 
}
