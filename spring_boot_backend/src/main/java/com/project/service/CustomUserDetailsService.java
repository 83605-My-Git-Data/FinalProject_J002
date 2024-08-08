package com.project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.dao.RegisterDao;
import com.project.entities.Register;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    RegisterDao registerDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Register register = registerDao.findByEmail(email);
        if (register == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(register.getEmail(), register.getPassword(), new ArrayList<>());
    }
}



