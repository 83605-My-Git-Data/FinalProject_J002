package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.Booking;

public interface BookingDao  extends JpaRepository<Booking, Long>{

}
