package com.project.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.project.dto.ApiResponse;
import com.project.dto.BookingDTO;

import com.project.dto.BookingdetailsrespDTO;

import com.project.dto.BookingRequestDto;

import com.project.entities.Status;

public interface BookingService  {
	
	  public ApiResponse BookPhotographer(Long userid, Long user_id, LocalDate date, LocalTime time,double Amount,String category);
	 public ApiResponse updateBookingStatus(Long bookingId, Status status);

	 public ApiResponse cancelBooking(Long id);
	 
	 public BookingdetailsrespDTO getBookingDetails(Long bookingId) ;
	       

	 
	 public List<BookingRequestDto> getBookingRequestForPhotographer(Long photographerId);

	
	 
	

}
