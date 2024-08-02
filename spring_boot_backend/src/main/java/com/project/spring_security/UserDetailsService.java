package com.project.spring_security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.Dao.RegisterDao;
import com.project.entities.Register;


@Service
@Transactional
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	 @Autowired
	 RegisterDao registerDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		  Register register  =  registerDao.findByEmail(email);
		  
		  if(register != null) {
			  return  register;
		  }
		
		return null;
	}

}
