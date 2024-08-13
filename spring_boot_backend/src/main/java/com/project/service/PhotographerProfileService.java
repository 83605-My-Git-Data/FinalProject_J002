package com.project.service;

import com.project.dto.ApiResponse;
import com.project.dto.BioDTO;
import com.project.dto.ExperienceDTO;
import com.project.dto.PhotographerdetailrespDTO;



public interface PhotographerProfileService {
	
	
	
	  public ApiResponse updateProfilePic(Long userId,String fileUrl);
	  
	  public ApiResponse updateBio(Long userId,BioDTO bioDTO);
	  
	  public ApiResponse updateExperienceLevel(Long userId,ExperienceDTO experienceDTO);
	  
	  public PhotographerdetailrespDTO getPhotographer(Long PhotographerId);
	  
	  public String getProfilePhotoPath(Long userId) ;

		}



