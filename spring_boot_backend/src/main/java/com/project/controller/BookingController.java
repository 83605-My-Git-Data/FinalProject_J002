package com.project.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ApiResponse;
import com.project.dto.BookingDTO;
import com.project.entities.Booking;
import com.project.entities.Status;
import com.project.service.BookingService;

	
@RestController
@RequestMapping("api/bookings")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	
	
		 @PostMapping
		    public ResponseEntity<?> createBooking(@RequestParam Long userid,
		                                                 @RequestParam Long user_id,
		                                                 @RequestParam String date, // Format: YYYY-MM-DD
		                                                 @RequestParam String time,@RequestParam double  Amount,@RequestParam String category ) { // Format: HH:mm:ss
		       
			 LocalDate EventDate = LocalDate.parse(date);
		        LocalTime EventTime = LocalTime.parse(time);
			 

		      try {
		    	  return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.BookPhotographer(userid, user_id, EventDate, EventTime,Amount,category));
		      }
		      catch(RuntimeException e)
		      {
		    	  return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(e.getMessage()));
		    	  
		      }
		    }

		    @PutMapping("/{id}/status")
		    public ResponseEntity<?> updateBookingStatus(@PathVariable Long id,
		                                                       @RequestParam Status status) {
		    	 try {
			    	  return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.updateBookingStatus(id, status));
			      }
			      catch(RuntimeException e)
			      {
			    	  return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(e.getMessage()));
			    	  
			      }
		    
		    }
		    

		
		
	}


