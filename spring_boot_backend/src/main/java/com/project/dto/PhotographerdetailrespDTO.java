package com.project.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class PhotographerdetailrespDTO {
	
	
	
	       
	            private String profilePhoto;
	            private String name;
	            private List<String> image;
	            private String bio; 
	            private String description; 
	            private long price; 
	            private String phoneNumber;

	       
	            public PhotographerdetailrespDTO(String profilePhoto, String name, String bio, String description, long price, String phoneNumber) {
	                this.profilePhoto = profilePhoto;
	                this.name = name;
	                this.bio = bio;
	                this.description = description;
	                this.price = price;
	                this.phoneNumber = phoneNumber;
	            }
	        

	    }

	    
	



