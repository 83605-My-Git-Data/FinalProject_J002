package com.project.service;

import javax.transaction.Transactional;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DTO.ApiResponse;
import com.project.DTO.PhotographerProfileDTO;
import com.project.Dao.PhotographerProfileDao;
import com.project.Dao.RegisterDao;
import com.project.custom_exceptions.ResourceNotFoundException;
import com.project.entities.Photographer_Profile;
import com.project.entities.Register;


@Service
@Transactional
public class PhotographerProfileimpl implements PhotographerProfileService {
	

	private static final Logger logger = LoggerFactory.getLogger(PhotographerProfileService.class);
	
	@Autowired
	private RegisterDao registerDao;
	
	@Autowired
	private PhotographerProfileDao photographerProfileDao;
	
	@Autowired
	private ModelMapper mapper;
	
	

	@Override
	public ApiResponse createorupdateprofile(PhotographerProfileDTO dto) {
		// TODO Auto-generated method stub
		String message="Photographer profile successfully updated";
		
		Register register=registerDao.findById(dto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		
		if(!register.getRole().equals("ROLE_PHOTOGRAPHER"))
		{
			 throw new RuntimeException("User is not a photographer");
			
			
		}
		try {
		
		Photographer_Profile profile=mapper.map(dto, Photographer_Profile.class);
		  photographerProfileDao.save(profile);
		  return new ApiResponse(message);
	}
		catch(Exception e) {
            logger.error("Registration failed", e);
            return new ApiResponse("Registration failed: " + e.getMessage());
		
		}
	}
		
	  public ApiResponse  updateProfilePic(Long userId,String fileUrl)
	  {
		  String message="photographer profile pic updated successsfully";
		 
	Photographer_Profile profile =photographerProfileDao.findById(userId).orElseThrow(() -> 
	new ResourceNotFoundException("User not found"));
		  if(profile!=null) {
			  profile.setProfilePhoto(fileUrl);
			  photographerProfileDao.save(profile); 
		  } 
		  return new ApiResponse(message);
		  
	
		  
		  
	  }



	
	
	

}
