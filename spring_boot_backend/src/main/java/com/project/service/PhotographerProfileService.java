package com.project.service;

import com.project.DTO.ApiResponse;
import com.project.DTO.PhotographerProfileDTO;


public interface PhotographerProfileService {
	
	public ApiResponse createorupdateprofile(PhotographerProfileDTO dto);
	
	  public ApiResponse updateProfilePic(Long userId,String fileUrl);

}
