package com.springboot.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.dto.IntensityDto;
import com.springboot.app.model.Intensity;
import com.springboot.app.repository.IntensityRepository;
import com.springboot.app.utility.EntityToDto;

@Service
@Transactional
public class IntensityService {

	private final IntensityRepository intensityRepository;
	
	public IntensityService(IntensityRepository intensityRepository) {
		this.intensityRepository = intensityRepository;
	}
	
	public List<IntensityDto> findAllIntensities(){
		List<Intensity> intensities = intensityRepository.findAll();
		return intensities.stream()
							.map(EntityToDto::ConvertToDto)
							.collect(Collectors.toList());
	}

}
