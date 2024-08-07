package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.Categories;

public interface CategoriesDao  extends JpaRepository<Categories, Long>{
	

}
