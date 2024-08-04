package com.project.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
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
@AllArgsConstructor
@ToString
@Table(name="photographerprofile")

public class Photographer_Profile {
	
	
	
	@Id
	
	@Column(name="user_id")
	private Long  userId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id",nullable = false)
	private Register register;
	
	@Enumerated(EnumType.STRING) 
	@Column(length = 20)
	
	private Experience ExperienceLevel;
	
	
	private String profilePhoto;
	@Column(length = 25, name="Bio")
	private String Bio;

	@PrePersist
    public void prePersist() {
       
        if (Bio == null) {
            Bio = "Passionate photographer with a keen eye for capturing life's "
            		+ "most cherished moments. Whether it's a wedding, a special event,"
            		+ " or a simple portrait, I strive to deliver stunning and memorable "
            		+ "images that tell a unique story";
        }
       
    }
	
	@Column(length=30)
	
	private String ExtensionColumn;
	
	
	
	
	
	
	
	
	
	
	

}
