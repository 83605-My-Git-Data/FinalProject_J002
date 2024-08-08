package com.project.dto;

import java.util.ArrayList;
import java.util.List;


import lombok.Getter;

import lombok.Setter;

@Setter
@Getter


public class PhotographerRespDTO {
	
	
	

	private Long id;
	 private String fullName;
	private List<String> image;
	private Long  price;
	
	private String profilePhoto;
	
	
	 public PhotographerRespDTO(Long id, String fullName, String profilePhoto, Long price) {
	        this.id = id;
	        this.fullName = fullName;
	        this.profilePhoto = profilePhoto;
	        this.price = price;
	        this.image = new ArrayList<>();  // Initialize with an empty list
	    }
	
	
	public void addImage(String imageUrl) {
        this.image.add(imageUrl);
    }
	


	

	
//	package com.project.dto;
//
//	public class PhotographerRespDTO {
//	    private long id;
//	   
//	    private String profilePhoto;
//	    private String image;
//	    private long price;
//
//	    public PhotographerRespDTO(long id, String fullName, String profilePhoto, String image, long price) {
//	        this.id = id;
//	        this.fullName = fullName;
//	        this.profilePhoto = profilePhoto;
//	        this.image = image;
//	        this.price = price;
//	    }

	    // Getters and setters
	}

	
	
	



