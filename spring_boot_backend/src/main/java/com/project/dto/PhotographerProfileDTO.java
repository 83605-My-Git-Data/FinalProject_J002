package com.project.dto;




import javax.validation.constraints.NotEmpty;

import com.project.entities.Experience;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PhotographerProfileDTO {
	
	
	
	private Long userId;

	@NotEmpty(message = "Experience level is mandatory field")
	private Experience ExperienceLevel;
	
	
	private String profilePhoto;
	
	private String Bio;
	
	
	
	
	

}
