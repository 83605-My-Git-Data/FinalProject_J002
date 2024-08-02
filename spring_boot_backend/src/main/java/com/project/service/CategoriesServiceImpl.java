package com.project.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DTO.ApiResponse;
import com.project.DTO.CategoriesDTO;
import com.project.Dao.CategoriesDao;
import com.project.entities.Categories;


@Service
@Transactional
public class CategoriesServiceImpl  implements CategoriesService{

	
	
	@Autowired
	public CategoriesDao categoriesDao;
	
	@Autowired
	public ModelMapper mapper;
	
	@Override
	public ApiResponse AddCategories(CategoriesDTO DTO) {
		
		String message="category added successfully";
		
		Categories categories=mapper.map(DTO, Categories.class);
		
		categoriesDao.save(categories);
		return new ApiResponse(message);
	}
	

}
