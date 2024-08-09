package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.project.dto.BookingdetailsrespDTO;


import com.project.dto.BookingRequestDto;
import com.project.entities.Booking;

public interface BookingDao  extends JpaRepository<Booking, Long>{
	

	    
	    @Query("SELECT new  com.project.dto.BookingdetailsrespDTO ( CONCAT(R.firstName, ' ', R.lastName), R.phoneNo, B.EventDate, B.EventTime, B.status )" +
	           "FROM Booking B " +
	           "JOIN B.register R " +
	           "WHERE B.id = :bookingId")
	   
	

		

		BookingdetailsrespDTO findBookingDetailsById( @Param("bookingId")Long bookingId);

	 @Query("SELECT new com.project.dto.BookingRequestDto(" +
	           "r.firstName || ' ' || r.lastName, b.EventDate, b.EventTime, b.Amount, b.category) " +
	           "FROM Booking b " +
	           "JOIN b.register r " +
	           "WHERE b.photographer_Profile.id = :photographerId")
	    List<BookingRequestDto> findBookingRequestsByPhotographerId(@Param("photographerId") Long photographerId);


}
