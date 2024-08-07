package com.project.service;

import java.util.List;

import com.project.dto.ApiResponse;
import com.project.dto.CategoriesDTO;
import com.project.entities.Categories;

public interface CategoriesService {
	
	public ApiResponse AddCategories(CategoriesDTO DTO);

	public List<Categories> getAllCategories();
	
	
}
