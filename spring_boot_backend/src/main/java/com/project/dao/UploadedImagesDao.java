package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.entities.Categories;
import com.project.entities.Photographer_Profile;
import com.project.entities.Uploaded_Images;

public interface UploadedImagesDao extends JpaRepository<Uploaded_Images,Long > {

	
	@Query("SELECT U.image, U.photographer.userId " +
		       "FROM Uploaded_Images U " +
		       "JOIN U.category C " +
		       "WHERE C.id = :id")
		List<Object[]> findImagesByCategoryId(@Param("id") Long id);
		
		
List<Uploaded_Images> findByPhotographerAndCategory(Photographer_Profile photographer, Categories category);
		
		
		
		


}
