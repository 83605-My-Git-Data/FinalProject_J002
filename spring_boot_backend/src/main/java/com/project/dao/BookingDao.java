package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.dto.BookingdetailsrespDTO;


import com.project.entities.Booking;

public interface BookingDao  extends JpaRepository<Booking, Long>{
	
	    
	    @Query("SELECT new  com.project.dto.BookingdetailsrespDTO ( CONCAT(R.firstName, ' ', R.lastName), R.phoneNo, B.EventDate, B.EventTime, B.status )" +
	           "FROM Booking B " +
	           "JOIN B.register R " +
	           "WHERE B.id = :bookingId")
	   
	

		

		BookingdetailsrespDTO findBookingDetailsById( @Param("bookingId")Long bookingId);


}
