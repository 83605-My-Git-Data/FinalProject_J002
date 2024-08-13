package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.dto.CategoryResponceDto;
import com.project.entities.Categories;
import com.project.entities.Photographer_Categories;
import com.project.entities.Photographer_Profile;

public interface PhotographerCategoriesDao extends JpaRepository<Photographer_Categories, Long> {
	boolean existsByPhotographerAndCategory(Photographer_Profile photographer, Categories category);
	

	@Query("SELECT new com.project.dto.CategoryResponceDto(c.id,c.categoryname) " +
	           "FROM Photographer_Categories pc " +
	           "JOIN pc.category c " +
	           "WHERE pc.photographer.id = :userId")
	    List<CategoryResponceDto> findCategoriesByPhotographerId(@Param("userId") Long userId);
}
