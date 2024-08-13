package com.project.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.entities.Uploaded_Images;
import com.project.service.UploadedImagesService;

@CrossOrigin("*")
@RestController
@RequestMapping("images")

public class UploadedImagesController {
	
	
	    @Autowired
	    private UploadedImagesService uploadedImageService;

	    @PostMapping("/{photographerid}/{categoryid}")
	    public ResponseEntity<List<Uploaded_Images>> uploadImages(@PathVariable Long photographerid,
	                                                            @PathVariable Long categoryid,
	                                                            @RequestParam("files") List<MultipartFile> files) throws IOException {
	    	 List<Uploaded_Images> uploadedImages = uploadedImageService.Addimages(photographerid, categoryid, files);
				return new ResponseEntity<>(uploadedImages, HttpStatus.CREATED);
	    }
	}

	


