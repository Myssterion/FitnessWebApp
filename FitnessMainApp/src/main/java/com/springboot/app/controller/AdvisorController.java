package com.springboot.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.model.Advisor;
import com.springboot.app.service.AdvisorService;

@RestController
@RequestMapping("/advisor")
public class AdvisorController {
	
	private final AdvisorService advisorService;

	public AdvisorController(AdvisorService advisorService) {
		this.advisorService = advisorService;
	}

	@GetMapping
	public ResponseEntity<List<Advisor>> getAllAdvisors(){
		List<Advisor> advisors = advisorService.findAllAdvisors();
		return new ResponseEntity<>(advisors,HttpStatus.OK);
	}

}