package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.ApiResponse;
import com.project.dto.CategoriesDTO;
import com.project.dao.CategoriesDao;
import com.project.entities.Categories;


@Service
@Transactional
public class CategoriesServiceImpl  implements CategoriesService{

	
	
	@Autowired
	public CategoriesDao categoriesdao;
	
	@Autowired
	public ModelMapper mapper;
	
	@Override
	public ApiResponse AddCategories(CategoriesDTO DTO) {
		
		String message="category added successfully";
		
		Categories categories=mapper.map(DTO, Categories.class);
		
		categoriesdao.save(categories);
		return new ApiResponse(message);
	}

	@Override
	public List<Categories> getAllCategories() {
		List<Categories> list = categoriesdao.findAll();
		return list;
	}

	 
	

}
