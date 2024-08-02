package com.project.dto;



	import java.time.LocalDateTime;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.EnumType;
	import javax.persistence.Enumerated;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.project.entities.Gender;
import com.project.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
	import lombok.NoArgsConstructor;
	import lombok.Setter;
	import lombok.ToString;



	

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



