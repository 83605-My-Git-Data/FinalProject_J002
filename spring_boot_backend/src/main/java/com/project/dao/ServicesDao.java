package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.dto.ServicesRespDto;
import com.project.entities.Services;

public interface ServicesDao extends JpaRepository<Services, Long> {
	
	
	
	@Query("SELECT new com.project.dto.ServicesRespDto(c.categoryname, s.price, s.description) " +
		       "FROM Services s " +
		       "JOIN s.category c " +
		       "JOIN Photographer_Categories pc ON s.photographer = pc.photographer AND s.category = pc.category " +
		       "WHERE s.photographer.id = :userId")
		List<ServicesRespDto> findPhotographerServices(@Param("userId") Long userId);

	
	
//	@Query("SELECT new com.project.dto.ServicesRespDto(c.categoryname,s.price,s.description)"+
//	"FROM Services s "+
//			"JOIN Categories c ON s.category = c.id "+
//			"JOIN Photographer_Categories pc ON s.photographer = pc.photographer AND s.category = pc.category "+
//			"WHERE s.photographer  = :userId")
//	List<ServicesRespDto> findPhotographerServices(@Param("userId") Long userId);

}


//@Query("SELECT new com.yourpackage.PhotographerServiceDTO(c.categoryName, s.price, s.description) " +
//        "FROM Service s " +
//        "JOIN Category c ON s.categoryId = c.id " +
//        "JOIN PhotographerCategory pc ON s.photographerId = pc.photographerId AND s.categoryId = pc.categoryId " +
//        "WHERE s.photographerId = :photographerId")
// List<PhotographerServiceDTO> findPhotographerServices(@Param("photographerId") Long photographerId);