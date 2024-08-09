package com.project.service;

import java.util.List;

import com.project.dto.ApiResponse;
import com.project.dto.CategoryResponceDto;
import com.project.dto.PhotographerCategoriesReqDto;

public interface PhotographerCategoriesService {

	public ApiResponse addCategoryToPhotogarpher(PhotographerCategoriesReqDto photographerCategoriesReqDto);
	public List<CategoryResponceDto> getCategoriesForPhotographer(Long userId);
}
