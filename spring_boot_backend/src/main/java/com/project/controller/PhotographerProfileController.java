package com.project.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.project.service.PhotographerProfileService;



import com.project.dto.ApiResponse;
import com.project.dto.BioDTO;
import com.project.dto.ExperienceDTO;
import com.project.dto.PhotographerRespDTO;
import com.project.dto.PhotographerdetailrespDTO;

@CrossOrigin("*")
@RestController
@RequestMapping("photographer_profile")

public class PhotographerProfileController {
	
	
	
	 @Value("${file.upload-dir}")
	    private String uploadDir;
	
	@Autowired
	public PhotographerProfileService photographerProfileService;
	
	
	
	@PostMapping("/updateBio/{userId}")
	public ResponseEntity<?> updateBio(@RequestBody @Valid BioDTO bioDTO,@PathVariable Long userId ){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(photographerProfileService.updateBio(userId, bioDTO));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));		
			
	   }
	}
	
	
	
	@PostMapping("/updateExperienceLevel/{userId}")
	public ResponseEntity<?> updateExperince (@RequestBody @Valid ExperienceDTO experienceDTO,@PathVariable Long userId){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(photographerProfileService.updateExperienceLevel(userId, experienceDTO));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));		
			
	   }
		
	}
	
	
	
	
	
	
	
	
	   @PostMapping("/uploadProfilePic/{userId}")
	    public ResponseEntity<String> uploadProfilePic(@PathVariable Long userId,
	                                                   @RequestParam("file") MultipartFile file) 
	   {
		   try {
               
         File directory = new File(uploadDir); // Create the directory if it doesn't exist
         if (!directory.exists()) {
             directory.mkdirs();
         }

        
         String fileName = file.getOriginalFilename(); // Define the file path and store the file
         Path filePath = Paths.get(uploadDir, fileName);
         Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

         // Generate the URL for the uploaded file
         String fileUrl = "http://localhost:8080/files/" + fileName;

         // Update the photographer profile with the file URL
         photographerProfileService.updateProfilePic(userId, fileUrl);

         return new ResponseEntity<>("Profile picture uploaded successfully. URL: " + fileUrl, HttpStatus.OK);
     } catch (IOException e) {
         return new ResponseEntity<>("File upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     }
 }
     @GetMapping("/{id}/photographerdetails")
	   public ResponseEntity<PhotographerdetailrespDTO>  getPhotographerdetail(@PathVariable("id") Long PhotographerId)
	   {
		   PhotographerdetailrespDTO photographerDetail = photographerProfileService.getPhotographer(PhotographerId);
	        if (photographerDetail == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(photographerDetail);
	   }
	   
     

     @GetMapping("/{userId}/profile-photo")
     public ResponseEntity<String> getProfilePhoto(@PathVariable Long userId) {
         String photoPath = photographerProfileService.getProfilePhotoPath(userId);
         if (photoPath == null) {
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.status(HttpStatus.OK).body(photoPath);
     }
 }
	   
	
	





   

 

 
       


