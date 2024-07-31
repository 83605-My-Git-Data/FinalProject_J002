package com.project.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter

@NoArgsConstructor
@ToString

@Table(name="Register")
public class Register {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@Column(name ="Firstname", length = 25)
	private  String firstname;
	@Column(name = "Lastname", length = 25)
	private String lastname;
	@Column(length = 20, unique = true)
	private String email;
	
	
	@Column(length=30)
	private String phoneno;
	@Column(length = 25, nullable = false)
	
	private String password;
	
	@Enumerated(EnumType.STRING) 
	@Column(length = 20)
	private Gender gender;
	
	@Enumerated(EnumType.STRING) 
	@Column(length = 20)
	private Role role;
	
	private LocalDateTime createdTime;
	
	private String ExtensionColumn;

	public Register(int id, String firstname, String lastname, String email, String phoneno, String password,
			Gender gender, Role role, LocalDateTime createdTime, String extensionColumn) {
		super();
		Id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phoneno = phoneno;
		this.password = password;
		this.gender = gender;
		this.role = role;
		this.createdTime = createdTime;
		ExtensionColumn = extensionColumn;
	}
	
}
