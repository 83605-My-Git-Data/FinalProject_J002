package com.project.service;

import java.time.LocalDate;
import java.time.LocalTime;

import com.project.dto.ApiResponse;
import com.project.dto.BookingDTO;
import com.project.entities.Status;

public interface BookingService  {
	
	  public ApiResponse BookPhotographer(Long userid, Long user_id, LocalDate date, LocalTime time,double Amount,String category);
	 public ApiResponse updateBookingStatus(Long bookingId, Status status);
	
	 
	

}
