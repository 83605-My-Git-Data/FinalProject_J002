package com.project.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.custom_exceptions.ResourceNotFoundException;
import com.project.dao.CategoriesDao;
import com.project.dao.PhotographerProfileDao;
import com.project.dao.ServicesDao;
import com.project.dto.ApiResponse;
import com.project.dto.ServicesDto;
import com.project.entities.Categories;
import com.project.entities.Photographer_Profile;
import com.project.entities.Services;



@Service
@Transactional
public class ServicesServiceImpl implements ServicesService {
	
	
	@Autowired
	PhotographerProfileDao photographerProfileDao;
	
	@Autowired
	CategoriesDao categoriesDao;
	
	
	@Autowired
	ServicesDao servicesDao;
	
	
	
	

	@Override
	public ApiResponse addAService(ServicesDto servicesDto) {
		System.out.println("inside service");
		
		Photographer_Profile profile= photographerProfileDao.findById(servicesDto.getPhotographerid())
			 .orElseThrow(()-> new ResourceNotFoundException("Photographer does not exists"));
	 

	 
	 
	     Categories category  =  categoriesDao.findById(servicesDto.getCategoryid())
	    		 .orElseThrow(()-> new ResourceNotFoundException("Category does not exists"));
	     
	    Services services = new Services();
	    services.setPrice(servicesDto.getPrice());
	    services.setDescription(servicesDto.getDescription());
	    services.setPhotographer(profile);
	    services.setCategory(category);
	    
	    servicesDao.save(services);
	     return new ApiResponse("Service added successfully for photographer");
	     
		
	}
 
}
