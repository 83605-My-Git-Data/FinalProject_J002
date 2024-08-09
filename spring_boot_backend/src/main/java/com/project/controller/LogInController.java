package com.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dao.PhotographerProfileDao;
import com.project.dao.RegisterDao;
import com.project.dto.ApiResponse;
import com.project.dto.JwtResponceDTO;
import com.project.dto.registerDto;
import com.project.dto.signInDto;
import com.project.entities.Photographer_Profile;
import com.project.entities.Register;
import com.project.entities.Role;
import com.project.jwt.JwtHelper;

import com.project.service.LogInService;
import com.project.service.PhotographerProfileService;


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
	 JwtHelper jwtHelper;
	 
	 @Autowired
		RegisterDao registerDao;
	 
	 @Autowired
		PhotographerProfileDao photographerProfileDao;

	
	
	@PostMapping
	public ResponseEntity<?> signIn(@RequestBody @Valid signInDto signInDto) {
		
		try {
			 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword()));
		        final UserDetails userDetails = logInService.loadUserByUsername(signInDto.getEmail());
		        
		        Register register =  registerDao.findByEmail(userDetails.getUsername());
		        
		    Role role  =   register.getRole();
		    
		    if("ROLE_PHOTOGRAPHER".equals(role)) {
				  
				  Long userId = register.getId();
				 
				  if (!photographerProfileDao.existsByUserId(userId)) {
		                Photographer_Profile profile = new Photographer_Profile();
		                profile.setUserId(userId);
		                photographerProfileDao.save(profile);
		            }		  
				  
			  }
		    
		    
		    String nameOfUser = register.getFirstName()+" "+register.getLastName();
		        
		        final String jwtToken = jwtHelper.generateToken(userDetails);
		        JwtResponceDTO jwtResponceDTO = new JwtResponceDTO(jwtToken, nameOfUser);
		        
		        return new ResponseEntity<>(jwtResponceDTO,HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
       
       
    }
	
//	@PostMapping()
//	public ResponseEntity<?> signIn(@RequestBody @Valid signInDto signInDto) {
//		try {
//			
//			
//			UsernamePasswordAuthenticationToken token=new 
//					UsernamePasswordAuthenticationToken(signInDto.getEmail(), 
//							signInDto.getPassword());
//			
//			
//			Authentication verifiedToken = authMgr.authenticate(token);
//			
//			
//			
//		   String jwt =   jwtUtility.generateJwtToken(verifiedToken);
//		   
//		   Claims claims =  jwtUtility.parseJwt(jwt);
//		   
//		   String role = claims.get("Role", String.class);
//		   
//		   String nameOfUser = claims.get("Name", String.class);
//		   System.out.println(nameOfUser);
//		   
//		  if("ROLE_PHOTOGRAPHER".equals(role)) {
//			  
//			  Long userId = claims.get("id", Long.class);
//			 
//			  if (!photographerProfileDao.existsByUserId(userId)) {
//	                Photographer_Profile profile = new Photographer_Profile();
//	                profile.setUserId(userId);
//	                photographerProfileDao.save(profile);
//	            }		  
//			  
//		  }
//		  
//		  JwtResponceDTO jwtResponceDTO = new JwtResponceDTO(jwt, nameOfUser);
//			
//			return new ResponseEntity<>(jwtResponceDTO,HttpStatus.OK);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
//		}
//		 
//		
//	}
	
	

}
