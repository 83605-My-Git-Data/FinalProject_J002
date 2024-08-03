package com.project.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class signInDto {
	
	@Email(message="invalid email format")
	@NotEmpty(message = "email can not be null")

	private String email;
	
	

	@NotEmpty(message="password can not empty")
	private String password;
	

}
