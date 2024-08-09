package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.custom_exceptions.ResourceNotFoundException;
import com.project.dao.CategoriesDao;
import com.project.dao.PhotographerCategoriesDao;
import com.project.dao.PhotographerProfileDao;
import com.project.dto.ApiResponse;
import com.project.dto.CategoryResponceDto;
import com.project.dto.PhotographerCategoriesReqDto;
import com.project.entities.Categories;
import com.project.entities.Photographer_Categories;
import com.project.entities.Photographer_Profile;




@Service
@Transactional
public class PhotographerCategoriesServiceimpl  implements PhotographerCategoriesService {
	
	
	
	@Autowired
	CategoriesDao categoriesDao;
	
	@Autowired
	PhotographerProfileDao photographerProfileDao;
	
	
	@Autowired
	PhotographerCategoriesDao photographerCategoriesDao;
	
	

	@Override
	public ApiResponse addCategoryToPhotogarpher(PhotographerCategoriesReqDto photographerCategoriesReqDto) {
		
		
	Photographer_Profile photographer = photographerProfileDao.findById(photographerCategoriesReqDto.getPid()).orElseThrow(()->new ResourceNotFoundException("Invalid photographer id"));

	 Categories category =    categoriesDao.findById(photographerCategoriesReqDto.getCid()).orElseThrow(()-> new ResourceNotFoundException("Invalid category id"));
	 
	 

     boolean categoryExists = photographerCategoriesDao.existsByPhotographerAndCategory(photographer, category);

     if (categoryExists) {
         return new ApiResponse("Category already added to photographer");
     }
	 
	 Photographer_Categories photographer_Categories = new Photographer_Categories();
	 photographer_Categories.setPhotographer(photographer);
	 photographer_Categories.setCategory(category);
	 
	 
	 photographerCategoriesDao.save(photographer_Categories);
	 
	 return new ApiResponse("Succesfully added category to photographer");
	
	}



	@Override
	public List<CategoryResponceDto> getCategoriesForPhotographer(Long userId) {
		return photographerCategoriesDao.findCategoriesByPhotographerId(userId);
	}

}
