package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ApiResponse;
import com.project.dto.CategoriesDTO;
import com.project.dto.PhotographerRespDTO;
import com.project.service.CategoriesService;


@CrossOrigin("*")
@RestController
@RequestMapping("Categories")
public class CategoriesController {
	
	@Autowired
	CategoriesService categoriesservice;
	
	
	@PostMapping
	public ResponseEntity<?> PostCategories(CategoriesDTO DTO)
	{
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(categoriesservice. AddCategories(DTO));
		} catch (RuntimeException e)
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
	
		
	}
	
	@GetMapping
	public ResponseEntity<?> getAllCategories() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(categoriesservice.getAllCategories());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	
	 @GetMapping("/photographers")
	    public List<PhotographerRespDTO> getPhotographersByCategory(@RequestParam Long id) {
	        return categoriesservice.getPhotographersByCategory(id);
	    }
	
	
	
}
	

	
	
	 
