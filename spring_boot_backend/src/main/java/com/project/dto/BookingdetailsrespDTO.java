package com.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.project.entities.Status;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class BookingdetailsrespDTO {
	


	
	    private String userName;
	    
	    
	    private String phoneNo;
	    
	    private LocalDate EventDate;
	    private LocalTime EventTime;
	    private Status status;

	    public  BookingdetailsrespDTO (String userName, String phoneNo, LocalDate EventDate, LocalTime EventTime, Status status) {
	        this.userName = userName;
	        this.phoneNo = phoneNo;
	        this.EventDate = EventDate;
	        this.EventTime = EventTime;
	        this.status = status;
	    }

	 
	}



