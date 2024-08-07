package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ApiResponse;
import com.project.service.BookingService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin("*")
@RestController
@RequestMapping("/bookingrequest/{photographerId}")
public class BookingRequestController {
	
	
	@Autowired
	BookingService bookingService;
	
	@GetMapping()
	public ResponseEntity<?> getBookingReqForPhotographer(@PathVariable Long photographerId) {
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingRequestForPhotographer(photographerId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));

		}
	}
	

}
