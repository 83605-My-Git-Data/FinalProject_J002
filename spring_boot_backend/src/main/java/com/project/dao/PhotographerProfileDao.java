package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.dto.PhotographerdetailrespDTO;
import com.project.entities.Photographer_Profile;

import io.swagger.v3.oas.annotations.Parameter;

public interface PhotographerProfileDao  extends JpaRepository<Photographer_Profile, Long>{
	
	
	

	    @Query("SELECT p.profilePhoto FROM Photographer_Profile p WHERE p.userId = :userId")
	    String findProfilePhotoPathByUserId(@Param("userId") Long userId);
	    @Query("SELECT p.Bio FROM Photographer_Profile p WHERE p.userId=:userId")
	    String findBioByUserId(@Param("userId")Long userId);
	    
	    
	    @Query("SELECT p.ExperienceLevel FROM Photographer_Profile p where p.userId=:userId")
	    String findExperienceByUserId(@Param("userId")Long userId);
	    
	

	
	 boolean existsByUserId(Long userId);
	 
	


}
		



	
	


