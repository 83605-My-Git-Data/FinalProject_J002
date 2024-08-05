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

import com.project.dao.PhotographerProfileDao;
import com.project.dto.ApiResponse;
import com.project.dto.signInDto;
import com.project.entities.Photographer_Profile;
import com.project.entities.Register;
import com.project.jwt.JwtUtility;
import com.project.service.LogInService;
import com.project.service.PhotographerProfileService;
import com.project.spring_security.UserDetailsService;

import io.jsonwebtoken.Claims;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LogInController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	LogInService logInService;
	
	
	@Autowired
	PhotographerProfileDao photographerProfileDao;
	
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
		   
		   Claims claims =  jwtUtility.parseJwt(jwt);
		   
		   String role = claims.get("Role", String.class);
		   
		   
		  if("ROLE_PHOTOGRAPHER".equals(role)) {
			  
			  Long userId = claims.get("id", Long.class);
			 
			  if (!photographerProfileDao.existsByUserId(userId)) {
	                Photographer_Profile profile = new Photographer_Profile();
	                profile.setUserId(userId);
	                photographerProfileDao.save(profile);
	            }		  
			  
		  }
			
			return new ResponseEntity<>(jwt,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
		
	}
	
	

}
