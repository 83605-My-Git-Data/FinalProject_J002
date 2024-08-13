package com.project.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.custom_exceptions.ResourceNotFoundException;
import com.project.dao.CategoriesDao;
import com.project.dao.PhotographerProfileDao;
import com.project.dao.UploadedImagesDao;

import com.project.entities.Categories;
import com.project.entities.Photographer_Profile;
import com.project.entities.Uploaded_Images;



@Service
@Transactional

public class UploadedImagesServiceImpl implements UploadedImagesService {

	
	
	@Autowired
	public UploadedImagesDao uploadedImagesDao;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@Autowired
	public PhotographerProfileDao photographerProfileDao;
	
	@Autowired
	public CategoriesDao categoriesDao;



	@Override
	public List<Uploaded_Images> Addimages(Long photographerId, Long categoryId, List<MultipartFile> files) throws IOException {
		
		
	Photographer_Profile photographer=photographerProfileDao.findById(photographerId)
.orElseThrow(() -> new ResourceNotFoundException("Photographer not found " ));
	
	
	Categories category=categoriesDao.findById(categoryId).orElseThrow(() ->
	new ResourceNotFoundException("Category not found  " ));
	List<Uploaded_Images> savedimages=new ArrayList<Uploaded_Images>();
	for(MultipartFile file:files)
	{
		String fileName=file.getOriginalFilename();
		 Path filePath = java.nio.file.Paths.get("files", fileName);
		 
		 Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		 
		 String fileUrl = "http://localhost:8080/files/" + fileName;
		 
         Uploaded_Images uploadedImage = new Uploaded_Images();
         uploadedImage.setImage(fileUrl);
         uploadedImage.setPhotographer(photographer);
         uploadedImage.setCategory(category);
         
         savedimages.add(uploadedImagesDao.save(uploadedImage));
         }
	        return savedimages;
		 
		 
		 
		
	
	
	
	}



	}

	
	
	


