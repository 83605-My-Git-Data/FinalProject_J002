package com.project.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AnotherController {
	
	@GetMapping("/new")
	public String getMethodName() {
		return new String("I am new");
	}
	

}
