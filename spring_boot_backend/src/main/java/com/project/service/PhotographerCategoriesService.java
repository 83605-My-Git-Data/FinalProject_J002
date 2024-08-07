package com.project.service;

import com.project.dto.ApiResponse;
import com.project.dto.PhotographerCategoriesReqDto;

public interface PhotographerCategoriesService {

	public ApiResponse addCategoryToPhotogarpher(PhotographerCategoriesReqDto photographerCategoriesReqDto);
}
