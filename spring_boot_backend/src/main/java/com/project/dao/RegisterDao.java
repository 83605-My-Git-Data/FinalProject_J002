package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.Register;

public interface RegisterDao extends JpaRepository<Register,Long> {
	
	Register findByEmail(String email);

}
