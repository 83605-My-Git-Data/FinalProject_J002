package com.project.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.validation.constraints.Future;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BookingId")
	
	private Long BookingId;
	
	@ManyToOne 
	@JoinColumn(name="userid",nullable = false)
	private Register register;
	
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private Photographer_Profile photographer_Profile;
	
	@Column(name="BookedDateTime")
	@CreationTimestamp
	private LocalDateTime BookedDateTime;

	@Column(name="EventDate")
	
	private LocalDate EventDate;
	
	

	@Column (name="EventTime")
	private LocalTime EventTime;
	
   private String category;
	
    @Enumerated(EnumType.STRING) 
	@Column(length = 20)
	private Status status;
	

	private double Amount;

	
	
	
	
	
	
	

}
