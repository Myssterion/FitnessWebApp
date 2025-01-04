package com.springboot.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.DifficultyDto;
import com.springboot.app.service.DifficultyService;

@RestController
@RequestMapping("/difficulty")
public class DifficultyController {
	
	private final DifficultyService difficultyService;

	public DifficultyController(DifficultyService difficultyService) {
		this.difficultyService = difficultyService;
	}

	@GetMapping
	public ResponseEntity<List<DifficultyDto>> getAllDifficulties(){
		List<DifficultyDto> difficulties = difficultyService.findAllDifficultys();
		return new ResponseEntity<>(difficulties,HttpStatus.OK);
	}

}