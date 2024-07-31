package com.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTO.ApiResponse;
import com.project.DTO.Registerrequest;
import com.project.service.RegisterService;


@RestController
@RequestMapping("/register")

public class RegisterController {
	
	@Autowired 
	private RegisterService registerService;
	
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> postregister(@RequestBody @Valid Registerrequest dto)
	{
		try {
			return ResponseEntity.ok
					(registerService.signup(dto));
		} catch (RuntimeException e)
		{
			System.out.println(e);
			return ResponseEntity.
					status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	
		
	}

}
