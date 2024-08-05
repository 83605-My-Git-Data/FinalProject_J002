package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponceDTO {
	
	private String jwt;
    private String email;

    
}
