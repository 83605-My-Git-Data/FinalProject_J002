package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ApiResponse;
import com.project.service.PhotographerCategoriesService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin("*")
@RestController
@RequestMapping("/photographer/categories/{userId}")
public class GetCategoryForPhotographerController {
	
	
	@Autowired
	PhotographerCategoriesService photographerCategoriesService;
	
	
	@GetMapping()
	public ResponseEntity<?> getCategoryForPhotographer(@PathVariable Long userId) {
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(photographerCategoriesService.getCategoriesForPhotographer(userId));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
		
	

}
}
