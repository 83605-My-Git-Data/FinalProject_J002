package com.project.service;

import com.project.dto.ApiResponse;
import com.project.dto.PhotographerProfileDTO;


public interface PhotographerProfileService {
	
	public ApiResponse createorupdateprofile(PhotographerProfileDTO dto);
	
	  public ApiResponse updateProfilePic(Long userId,String fileUrl);

}
