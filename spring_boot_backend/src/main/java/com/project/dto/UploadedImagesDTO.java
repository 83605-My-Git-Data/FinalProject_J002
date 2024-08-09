package com.project.dto;


import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor


public class UploadedImagesDTO {
	
@NotEmpty(message = " photographerid is mandatory")
	private Long photographerid;
	
	@NotEmpty (message="categoryid is mandatory ")
	private Long categoryid;
	
	
	@NotEmpty (message="image is mandatory")
	private String image;
	
	
	
	
	

}
