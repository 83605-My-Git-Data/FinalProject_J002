package com.project.DTO;



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
@AllArgsConstructor
@ToString

	public class Registerrequest {
		
		
	
		
		@NotEmpty(message = "firstname is mandatory field")
		private  String firstname;
		
		@NotEmpty(message = "firstname is mandatory field")
		private String lastname;
		
		@Email(message="invalid email format")
		@NotEmpty(message = "email can not be null")
	
		private String email;
		
		
		@NotEmpty(message = "phoneno can not empty")
		private String phoneno;
	
		@NotEmpty(message="password can not empty")
		private String password;
		
	
		private Gender gender;
		
	 
	
		private Role role;
		
		private LocalDateTime createdTime;
		
		private String ExtensionColumn;

	
		
		
		
		
		

	}



