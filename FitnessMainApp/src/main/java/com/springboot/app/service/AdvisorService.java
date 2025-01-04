package com.springboot.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.model.Advisor;
import com.springboot.app.repository.AdvisorRepository;

@Service
@Transactional
public class AdvisorService {

private final AdvisorRepository advisorRepository;
	
	public AdvisorService(AdvisorRepository advisorRepository) {
		this.advisorRepository = advisorRepository;
	}
	
	public List<Advisor> findAllAdvisors(){
		return advisorRepository.findAll();
	}


}
