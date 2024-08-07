package com.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.project.entities.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor 

public class BookingDTO {
	
	@NotBlank
    private Long userid;
	
	@NotBlank
	 private Long user_id;
	
	@NotBlank 
	private String category;
	
	
	
	@NotBlank
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate eventDate;

	
	
	
	@NotBlank

    private LocalTime  EventTime;
	
   private Status status;
   
	@NotEmpty
   private double Amount;
	
	

}
