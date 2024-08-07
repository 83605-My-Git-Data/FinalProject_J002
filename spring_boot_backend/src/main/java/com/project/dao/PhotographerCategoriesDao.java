package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.Categories;
import com.project.entities.Photographer_Categories;
import com.project.entities.Photographer_Profile;

public interface PhotographerCategoriesDao extends JpaRepository<Photographer_Categories, Long> {
	boolean existsByPhotographerAndCategory(Photographer_Profile photographer, Categories category);

}
