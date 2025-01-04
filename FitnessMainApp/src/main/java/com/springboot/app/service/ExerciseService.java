package com.springboot.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.dto.ExerciseDto;
import com.springboot.app.model.Exercise;
import com.springboot.app.repository.ExerciseRepository;
import com.springboot.app.utility.EntityToDto;


@Service
@Transactional
public class ExerciseService {

	private final ExerciseRepository exerciseRepository;
	
	public ExerciseService(ExerciseRepository exerciseRepository) {
		this.exerciseRepository = exerciseRepository;
	}
	
	public List<ExerciseDto> findAllExercises(){
		List<Exercise> exercises = exerciseRepository.findAll();
		return exercises.stream()
						.map(EntityToDto::ConvertToDto)
						.collect(Collectors.toList());
	}

}
