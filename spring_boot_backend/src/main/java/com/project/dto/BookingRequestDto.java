package com.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {
	
	private String userName;
	private LocalDate date;
	private LocalTime time;
	private double amount;
	private String categoryname;

}
