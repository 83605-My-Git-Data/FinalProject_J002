package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.dto.BookingRequestDto;
import com.project.entities.Booking;

public interface BookingDao  extends JpaRepository<Booking, Long>{
	
	 @Query("SELECT new com.project.dto.BookingRequestDto(" +
	           "r.firstName || ' ' || r.lastName, b.EventDate, b.EventTime, b.Amount, b.category) " +
	           "FROM Booking b " +
	           "JOIN b.register r " +
	           "WHERE b.photographer_Profile.id = :photographerId")
	    List<BookingRequestDto> findBookingRequestsByPhotographerId(@Param("photographerId") Long photographerId);

}
