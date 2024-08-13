package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dao.CategoriesDao;
import com.project.dao.PhotographerCategoriesDao;
import com.project.dto.ApiResponse;
import com.project.dto.PhotographerCategoriesReqDto;
import com.project.service.PhotographerCategoriesService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin("*")
@RestController
@RequestMapping("/photographer/addcategory")
public class PhotographerCategoryController {
	
	
	@Autowired
	CategoriesDao categoriesDao;
	
	@Autowired
	PhotographerCategoriesDao photographerCategoriesDao;
	
	
	@Autowired
	PhotographerCategoriesService photographerCategoriesService;
	
	
	@PostMapping()
	public ResponseEntity<?> addCategoryToPhotographer(@RequestBody PhotographerCategoriesReqDto photographerCategoriesReqDto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(photographerCategoriesService.addCategoryToPhotogarpher(photographerCategoriesReqDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
	}
	
	
	

}
