package com.project.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

public class Uploaded_Images {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long  id;
  
  
  @ManyToOne
  @JoinColumn(name="user_id")
  
  private Photographer_Profile photographer;
  
  @ManyToOne
  @JoinColumn(name="cid")
 private Categories category;
  
  private String image;
  
  
  
 
 
}
