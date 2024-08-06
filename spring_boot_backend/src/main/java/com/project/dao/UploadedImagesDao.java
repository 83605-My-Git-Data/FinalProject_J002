package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.Uploaded_Images;

public interface UploadedImagesDao extends JpaRepository<Uploaded_Images,Long > {

}
