package com.project.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.project.custom_exceptions.ResourceNotFoundException;
import com.project.dao.BookingDao;
import com.project.dao.PhotographerProfileDao;
import com.project.dao.RegisterDao;
import com.project.dto.ApiResponse;

import com.project.dto.BookingdetailsrespDTO;

import com.project.dto.BookingRequestDto;

import com.project.entities.Booking;

import com.project.entities.Photographer_Profile;
import com.project.entities.Register;
import com.project.entities.Status;

@Service
@Transactional

public class BookingServiceImpl implements BookingService {

	
	
	@Autowired
	public BookingDao bookingDao;
	
	@Autowired
	public RegisterDao registerDao;
	
	@Autowired
	public PhotographerProfileDao photographerProfileDao;
	
	
	@Autowired
	public ModelMapper modelMapper;
	

	
	@Override
	
	    public ApiResponse BookPhotographer(Long userid, Long user_id, LocalDate date, LocalTime time,double Amount,String category) {
	      
		
		   Register register  =  registerDao.findById(userid)
		    		 .orElseThrow(()-> new ResourceNotFoundException("user  does not exists"));
		   
		
		Photographer_Profile profile= photographerProfileDao.findById(user_id)
				 .orElseThrow(()-> new ResourceNotFoundException("Photographer does not exists"));
		 

		 
		 
		  
		
		Booking booking = new Booking();
	       
	        booking.setRegister(register);
	        booking.setPhotographer_Profile(profile);
	        booking.setEventDate(date);
	        booking.setEventTime(time);
	        booking.setAmount(Amount);
	        booking.setCategory(category);
	        booking.setStatus(Status.PENDING);
	       bookingDao.save(booking);
	       return new ApiResponse("photographer booked successfully");
	      
	    }

	    public ApiResponse  updateBookingStatus(Long bookingId,Status status) {
	        Booking booking = bookingDao.findById(bookingId)
	       .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
	        booking.setStatus(status);
	        bookingDao.save(booking);
	        return new ApiResponse("booking status updated successfully");
	    }

		@Override

		public ApiResponse cancelBooking(Long id) {
			
		Booking booking	=bookingDao .findById(id) .
				orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " ));
		
		booking.setStatus(Status.CANCELLED);
		bookingDao.save(booking);
		

			return new ApiResponse("Booking cancelled successfully");
		}

		@Override
		public BookingdetailsrespDTO getBookingDetails(Long bookingId) {
			
		  return bookingDao.findBookingDetailsById(bookingId);
		
		}
		

		public List<BookingRequestDto> getBookingRequestForPhotographer(Long photographerId) {
			 return bookingDao.findBookingRequestsByPhotographerId(photographerId);
		}


	  
	}



