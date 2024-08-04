package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.Services;

public interface ServicesDao extends JpaRepository<Services, Long> {

}
