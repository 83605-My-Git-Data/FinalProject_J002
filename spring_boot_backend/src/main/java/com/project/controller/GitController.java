package com.project.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class GitController {

	
	@GetMapping("/git")
	public String getMethodName() {
		return new String("gitt gittt eveyrwhere");
	}
	
}
