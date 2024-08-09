package com.project.dto;	
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import com.project.entities.Gender;
import com.project.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
	
@Setter
@Getter
@NoArgsConstructor


public class registerDto {
		
		@NotEmpty(message = "first name is mandatory")
		private  String firstName;
		
		@NotEmpty(message = "last name is mandatory")
		private String lastName;
		
		@Email(message="invalid email format")
		@NotEmpty(message = "email can not be null")
	
		private String email;
		
		
		@NotEmpty(message = "phone number cannot be empty")
		private String phoneNo;
	
		@NotEmpty(message="password can not empty")
		private String password;
		
	
		private Gender gender;
		
	     private Role role;
	

	}



