package com.springboot.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.dto.DifficultyDto;
import com.springboot.app.model.Difficulty;
import com.springboot.app.repository.DifficultyRepository;
import com.springboot.app.utility.EntityToDto;

@Service
@Transactional
public class DifficultyService {

	private final DifficultyRepository difficultyRepository;
	
	public DifficultyService(DifficultyRepository difficultyRepository) {
		this.difficultyRepository = difficultyRepository;
	}
	
	public List<DifficultyDto> findAllDifficultys(){
		List<Difficulty> difficulties = difficultyRepository.findAll();
		return difficulties.stream()
							.map(EntityToDto::ConvertToDto)
							.collect(Collectors.toList());
	}
	
	public Difficulty findById(Integer difficultyId) {
		Difficulty difficulty = difficultyRepository.findById(difficultyId).orElse(null);
		return difficulty;
	}

}
