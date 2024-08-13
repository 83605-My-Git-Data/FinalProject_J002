package com.project.service;

import java.util.List;

import javax.transaction.Transactional;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.ApiResponse;
import com.project.dto.BioDTO;
import com.project.dto.ExperienceDTO;
import com.project.dto.PhotographerdetailrespDTO;
import com.project.dao.PhotographerProfileDao;
import com.project.dao.RegisterDao;
import com.project.custom_exceptions.ResourceNotFoundException;
import com.project.entities.Photographer_Profile;
import com.project.entities.Register;
import com.project.entities.Role;


@Service
@Transactional
public class PhotographerProfileServiceimpl implements PhotographerProfileService {
	

	private static final Logger logger = LoggerFactory.getLogger(PhotographerProfileService.class);
	
	@Autowired
	private RegisterDao registerDao;
	
	@Autowired
	private PhotographerProfileDao photographerProfileDao;
	
	@Autowired
	private ModelMapper mapper;
	
	


		
	  public ApiResponse  updateProfilePic(Long userId,String fileUrl)
	  {
		  String message="photographer profile pic updated successsfully";
		 
		  Register register = registerDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Invalid id"));
	
		  
		  Role role = register.getRole();
		   
		    
		    if (role == null || role != Role.ROLE_PHOTOGRAPHER) {
		        throw new RuntimeException("User is not a photographer");
		    }
		  
		    
		    Photographer_Profile profile = photographerProfileDao.findById(register.getId())
		            .orElseThrow(() -> new ResourceNotFoundException("Photographer profile not found"));
		    
		   
		    profile.setProfilePhoto(fileUrl);
		    photographerProfileDao.save(profile);
	
		  
		  return new ApiResponse(message);
		  
	
		  
		  
	  }





	@Override
	public ApiResponse updateBio(Long userId, BioDTO bioDTO) {
		
		 Register register = registerDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Invalid id"));
			
		  
		  Role role = register.getRole();
		   
		       if (role == null || role != Role.ROLE_PHOTOGRAPHER) {
		        throw new RuntimeException("User is not a photographer");
		    }
		    
		    Photographer_Profile profile = photographerProfileDao.findById(register.getId())
		            .orElseThrow(() -> new ResourceNotFoundException("Photographer profile not found"));

		   
		    profile.setBio(bioDTO.getBio());

//		    }
		    photographerProfileDao.save(profile);


		    return new ApiResponse("Bio updated successfully");
		    
		
	}





	@Override
	public ApiResponse updateExperienceLevel(Long userId, ExperienceDTO experienceDTO) {
		Register register = registerDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Invalid id"));
		
		  
		  Role role = register.getRole();
		   
		    
		    if (role == null || role != Role.ROLE_PHOTOGRAPHER) {
		        throw new RuntimeException("User is not a photographer");
		    }
		    
		    Photographer_Profile profile = photographerProfileDao.findById(register.getId())
		            .orElseThrow(() -> new ResourceNotFoundException("Photographer profile not found"));

		    
		    profile.setExperienceLevel(experienceDTO.getExperienceLevel());

		   
		    photographerProfileDao.save(profile);


		    return new ApiResponse("Experience updated successfully");
		    
	}
	
	

 




	




	@Override
	public String getProfilePhotoPath(Long userId) {
		  
		        return photographerProfileDao.findProfilePhotoPathByUserId(userId);
		    

		
		        
		        
	}





	@Override
	public String getBio(Long userId) {
		
	return photographerProfileDao.findBioByUserId(userId);
	}





	@Override
	public String getExperience(Long userId) {
		return photographerProfileDao.findExperienceByUserId(userId);
		
	}
	
	






	

	
	
	

}
