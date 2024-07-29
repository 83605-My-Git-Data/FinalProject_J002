package com.example.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HomeController {
	
	@GetMapping("/hi")
	public String getMethodName() {
		return new String("Hi from app");
	}
	
	@GetMapping("/bye")
	public String getMethodNasme() {
		return new String("fuck you and byse");
	}
	
	

}
