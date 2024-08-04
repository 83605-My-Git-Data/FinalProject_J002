package com.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ApiResponse;
import com.project.dto.ServicesDto;
import com.project.service.ServicesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/services")
public class ServicesController {
	
	
	@Autowired
	private ServicesService servicesService;
	
	
	
	@PostMapping()
	public ResponseEntity<?> addService(@RequestBody @Valid ServicesDto servicesDto) {
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(servicesService.addAService(servicesDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	
	}
	

}
