package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTO.ApiResponse;
import com.project.DTO.CategoriesDTO;
import com.project.service.CategoriesService;

@RestController
@RequestMapping("Categories")
public class CategoriesController {
	
	@Autowired
	CategoriesService categoriesService;
	
	
	@PostMapping
	public ResponseEntity<?> PostCategories(CategoriesDTO DTO)
	{
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(categoriesService.AddCategories(DTO));
		} catch (RuntimeException e)
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
	
		
	}

}
