package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.dto.PhotographerdetailrespDTO;
import com.project.entities.Photographer_Profile;

public interface PhotographerProfileDao  extends JpaRepository<Photographer_Profile, Long>{
	
	

	    @Query("SELECT p.profilePhoto FROM Photographer_Profile p WHERE p.userId = :userId")
	    String findProfilePhotoPathByUserId(@Param("userId") Long userId);
	

	
	 boolean existsByUserId(Long userId);
	 
	


	 @Query("SELECT new com.project.dto.PhotographerdetailrespDTO(P.profilePhoto, CONCAT(R.firstName, ' ', R.lastName), " +
		       "P.Bio, S.description, S.price, R.phoneNo) " +
		       "FROM Photographer_Profile P " +
		       "JOIN Register R ON P.userId = R.id " +
		       "JOIN Services S ON P.userId = S.photographer.id " +
		       "WHERE P.userId = :photographerId")
		PhotographerdetailrespDTO findPhotographerBasicDetailById(@Param("photographerId") Long photographerId);

	    @Query("SELECT i.image " +
	           "FROM Uploaded_Images i " +
	           "WHERE i.photographer.id = :photographerId")
	    List<String> findImagesByPhotographerId(@Param("photographerId") Long photographerId);
	    
	    
	    
	    
	    @Query("SELECT p.Bio FROM Photographer_Profile p WHERE p.userId=:userId")
	    String findBioByUserId(@Param("userId")Long userId);
	    
	    
	    @Query("SELECT p.ExperienceLevel FROM Photographer_Profile p where p.userId=:userId")
	    String findExperienceByUserId(@Param("userId")Long userId);

	    
	    
	    
	    
	    
	}
		



	
	


