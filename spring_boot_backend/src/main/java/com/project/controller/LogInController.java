package com.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ApiResponse;
import com.project.dto.signInDto;
import com.project.entities.Register;
import com.project.jwt.JwtUtility;
import com.project.service.LogInService;
import com.project.spring_security.UserDetailsService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/login")
public class LogInController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	LogInService logInService;
	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	@Autowired
	JwtUtility jwtUtility;
	
	
	@Autowired
	private AuthenticationManager authMgr;
	
	@PostMapping()
	public ResponseEntity<?> signIn(@RequestBody @Valid signInDto signInDto) {
		try {
			
			
			UsernamePasswordAuthenticationToken token=new 
					UsernamePasswordAuthenticationToken(signInDto.getEmail(), 
							signInDto.getPassword());
		
			Authentication verifiedToken = authMgr.authenticate(token);
			
			
		   String jwt =   jwtUtility.generateJwtToken(verifiedToken);
			
			return new ResponseEntity<>(jwt,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
		
	}
	
	

}
