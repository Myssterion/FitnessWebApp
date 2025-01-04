package com.springboot.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.ExerciseDto;
import com.springboot.app.service.ExerciseService;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
	
	private final ExerciseService exerciseService;

	public ExerciseController(ExerciseService exerciseService) {
		this.exerciseService = exerciseService;
	}

	@GetMapping
	public ResponseEntity<List<ExerciseDto>> getAllExercises(){
		List<ExerciseDto> exercises = exerciseService.findAllExercises();
		return new ResponseEntity<>(exercises,HttpStatus.OK);
	}

}
