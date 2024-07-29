package com.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/hi")
	public String getMethodName() {
		return new String("hi there");
	}
	@GetMapping("/bye")
	public String getMetshodName() {
		return new String("Bye there fuck you");
	}
	
	

}
