package com.project.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServicesDto {
	
	
	@NotNull(message = "photographer id cannot be blank")
	private Long pid;
	
	@NotNull(message = "category id cannot be blank")
	private Long cid;
	
	@NotNull(message = "price cannot be blank")
	private Long price;
	
	
	@NotEmpty(message = "description cannot be blank")
	private String description;

}
