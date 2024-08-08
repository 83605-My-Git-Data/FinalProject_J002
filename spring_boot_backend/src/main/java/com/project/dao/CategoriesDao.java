package com.project.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.project.dto.PhotographerRespDTO;

import com.project.entities.Categories;

public interface CategoriesDao  extends JpaRepository<Categories, Long>{
	



	@Query("SELECT new com.project.dto.PhotographerRespDTO(" +
		       "R.id, CONCAT(R.firstName, ' ', R.lastName), PP.profilePhoto, S.price) " +
		       "FROM Services S " +
		       "JOIN S.photographer PP " +
		       "JOIN S.category C " + 
		       "JOIN Register R ON PP.userId = R.id " + 
		       "WHERE C.id = :id")
		List<PhotographerRespDTO> findPhotographerDetailsByCategoryId(@Param("id") Long id);
	
	



   		
   		


		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   

		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   



}
