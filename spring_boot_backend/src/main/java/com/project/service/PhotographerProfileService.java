package com.project.service;

import com.project.dto.ApiResponse;
import com.project.dto.BioDTO;
import com.project.dto.ExperienceDTO;



public interface PhotographerProfileService {
	
	
	
	  public ApiResponse updateProfilePic(Long userId,String fileUrl);
	  
	  public ApiResponse updateBio(Long userId,BioDTO bioDTO);
	  
	  public ApiResponse updateExperienceLevel(Long userId,ExperienceDTO experienceDTO);

}
