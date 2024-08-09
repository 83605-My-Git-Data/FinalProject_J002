package com.project.entities;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter

@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table(name="Register")
public class Register  implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name ="FirstName", length = 25)
	private  String firstName;
	
	
	@Column(name = "LastName", length = 25)
	private String lastName;
	
	
	@Column( name = "Email"  ,length = 20, unique = true)
	private String email;
	
	
	@Column(name = "PhoneNo", length=30)
	private String phoneNo;
	
	
	@Column(length = 255, nullable = false)
	private String password;
	
	
	@Enumerated(EnumType.STRING) 
	@Column(length = 20)
	private Gender gender;
	
	
	@Enumerated(EnumType.STRING) 
	@Column(length = 20)
	private Role role;
	
	@CreationTimestamp
	@Column(name = "CreatedOn")
	private LocalDateTime createdTime;
	
	
	private String ExtensionColumn;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	
}
