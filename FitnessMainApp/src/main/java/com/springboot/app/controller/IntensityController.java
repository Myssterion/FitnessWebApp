package com.springboot.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.IntensityDto;
import com.springboot.app.service.IntensityService;

@RestController
@RequestMapping("/intensity")
public class IntensityController {

	private final IntensityService intensityService;
	
	public IntensityController(IntensityService intensityService) {
		this.intensityService = intensityService;
	}
	
	@GetMapping
	public ResponseEntity<List<IntensityDto>> getAllIntensities(){
		List<IntensityDto> intensities = intensityService.findAllIntensities();
		return new ResponseEntity<>(intensities,HttpStatus.OK);
	}

}
