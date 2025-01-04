package com.springboot.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.ProgramInfoDto;
import com.springboot.app.dto.PaymentDto;
import com.springboot.app.model.RegularUserProgram;
import com.springboot.app.service.RegularUserProgramService;

@RestController
@RequestMapping("/regularUserProgram")
public class RegularUserProgramController {
	
	private final RegularUserProgramService regularUserProgramService;

	public RegularUserProgramController(RegularUserProgramService regularUserProgramService) {
		this.regularUserProgramService = regularUserProgramService;
	}

	@GetMapping
	public ResponseEntity<List<RegularUserProgram>> getAllRegularUserPrograms(){
		List<RegularUserProgram> regularUserPrograms = regularUserProgramService.findAllRegularUserPrograms();
		return new ResponseEntity<>(regularUserPrograms,HttpStatus.OK);
	}
	
	@GetMapping("/find/isSubscribed/{userId}/{programId}")
	public ResponseEntity<Boolean> getIsUserSubscribedToProgram(@PathVariable Integer userId, @PathVariable Integer programId) {
		boolean isSubscribed = regularUserProgramService.findIsUserSubscribedForProgram(userId, programId);
		return new ResponseEntity<>(isSubscribed, HttpStatus.OK);
	}

	@GetMapping("/find/subscribed/{userId}")
	public ResponseEntity<List<ProgramInfoDto>> getSubscribedProgramsById(@PathVariable Integer userId){
		List<ProgramInfoDto> programs = regularUserProgramService.findSubscribedForUser(userId);
		return new ResponseEntity<>(programs,HttpStatus.OK);
	}
	
	@PostMapping("/subscribe")
	public ResponseEntity<ProgramInfoDto> subscribeToProgram(@RequestBody PaymentDto payment) {
		ProgramInfoDto newProgram = regularUserProgramService.subscribeToProgram(payment);
		if(newProgram == null)
			return ResponseEntity.badRequest().build();
		else
			return new ResponseEntity<>(newProgram,HttpStatus.OK);
	}
}
