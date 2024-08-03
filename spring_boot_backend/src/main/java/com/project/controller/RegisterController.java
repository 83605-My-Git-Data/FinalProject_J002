package com.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ApiResponse;

import com.project.dto.registerDto;
import com.project.service.RegisterService;


@RestController
@RequestMapping("/signup")

public class RegisterController {
	
	@Autowired 
	private RegisterService registerService;
	
	
	
	@PostMapping
	public ResponseEntity<?> registerUser(@RequestBody @Valid registerDto dto)
	{
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(registerService.signUp(dto));
		} catch (RuntimeException e)
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
		
	
		
	}
	
	@GetMapping("/trial")
	public ResponseEntity<?> getMethodName() {
		return ResponseEntity.status(HttpStatus.CREATED).body("HIIIII it is succesfful");
	}
	
	
	@GetMapping("/check")
	public ResponseEntity<?> getMsethodName() {
		return ResponseEntity.status(HttpStatus.CREATED).body("HIIIII it is succesfful");
	}
	

}
