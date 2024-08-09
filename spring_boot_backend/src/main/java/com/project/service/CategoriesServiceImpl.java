package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.project.dto.ApiResponse;
import com.project.dto.CategoriesDTO;
import com.project.dto.PhotographerRespDTO;

import com.project.dao.CategoriesDao;
import com.project.dao.UploadedImagesDao;
import com.project.entities.Categories;


@Service
@Transactional
public class CategoriesServiceImpl  implements CategoriesService{

	
	
	@Autowired
	public CategoriesDao categoriesdao;
	
	@Autowired
	public UploadedImagesDao uploadedImagesDao;
	
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

	@Override
	public List<PhotographerRespDTO> getPhotographersByCategory(Long id) {
		
		 List<PhotographerRespDTO> photographerDetails = categoriesdao.findPhotographerDetailsByCategoryId(id);
		    Map<Long, PhotographerRespDTO> photographerMap = photographerDetails.stream()
		            .collect(Collectors.toMap(PhotographerRespDTO::getId, Function.identity()));

		    List<Object[]> images = uploadedImagesDao.findImagesByCategoryId(id);
		    for (Object[] imageData : images) {
		        Long photographerId = (Long) imageData[1];
		        String imageUrl = (String) imageData[0];
		        PhotographerRespDTO photographer = photographerMap.get(photographerId);
		        if (photographer != null) {
		            photographer.getImage().add(imageUrl);
		        }
		    }

		    return new ArrayList<>(photographerMap.values());


}
	}
	


