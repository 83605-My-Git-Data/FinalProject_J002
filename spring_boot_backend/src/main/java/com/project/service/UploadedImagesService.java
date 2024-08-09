package com.project.service;

import java.io.IOException;
import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.project.entities.Uploaded_Images;


public interface UploadedImagesService {
	
	public List<Uploaded_Images> Addimages(Long PhotographerId,Long CategoryId,List<MultipartFile> files ) throws IOException;
	
	

}
