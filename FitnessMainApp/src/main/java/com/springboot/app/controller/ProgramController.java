package com.springboot.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.FilterDto;
import com.springboot.app.dto.ProgramBasicDto;
import com.springboot.app.dto.ProgramDto;
import com.springboot.app.dto.ProgramInfoDto;
import com.springboot.app.service.ProgramService;

@RestController
@RequestMapping("/program")
public class ProgramController {

	private final ProgramService programService;

	public ProgramController(ProgramService programService) {
		this.programService = programService;
	}

	@GetMapping
	public ResponseEntity<List<ProgramInfoDto>> getAllPrograms() {
		List<ProgramInfoDto> programs = programService.findAllPrograms();
		return new ResponseEntity<>(programs, HttpStatus.OK);
	}
	
	@GetMapping("/seek")
	public ResponseEntity<List<ProgramBasicDto>> getAllProgramsWithSearch(@RequestParam String search,  @RequestParam(defaultValue = "5") int limit) {
		List<ProgramBasicDto> programs = programService.findAllProgramsWith(search, limit);
		return new ResponseEntity<>(programs, HttpStatus.OK);
	}
	
	@PostMapping("/filter")
	public ResponseEntity<?> receiveArguments(@RequestBody FilterDto filterDTO) {
		List<ProgramInfoDto> programs = programService.getFilteredPrograms(filterDTO);
		return new ResponseEntity<>(programs, HttpStatus.OK);
    }
	
	@GetMapping("/find/byCategory/{categoryId}")
	public ResponseEntity<List<ProgramInfoDto>> getAllProgramsWithCategory(@PathVariable Integer categoryId) {
		List<ProgramInfoDto> programs = programService.findAllProgramsWithCategory(categoryId);
		return new ResponseEntity<>(programs, HttpStatus.OK);
	}
	
	@GetMapping("/find/byAttribute/{attributeId}")
	public ResponseEntity<List<ProgramInfoDto>> getAllProgramsWithAttribute(@PathVariable Integer attributeId) {
		List<ProgramInfoDto> programs = programService.findAllProgramsWithAttribute(attributeId);
		return new ResponseEntity<>(programs, HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<ProgramDto> getProgramById(@PathVariable Integer id){
		ProgramDto programDto = programService.findById(id);
		if(programDto == null)
			return ResponseEntity.notFound().build();
		else
			return new ResponseEntity<>(programDto,HttpStatus.OK);
	}
	
	@GetMapping("/find/active/{userId}")
	public ResponseEntity<List<ProgramInfoDto>> getActiveProgramsById(@PathVariable Integer userId){
		List<ProgramInfoDto> programs = programService.findActiveForUser(userId);
		return new ResponseEntity<>(programs,HttpStatus.OK);
	}
	
	@GetMapping("/find/inactive/{userId}")
	public ResponseEntity<List<ProgramInfoDto>> getInactiveProgramsById(@PathVariable Integer userId){
		List<ProgramInfoDto> programs = programService.findInactiveForUser(userId);
		return new ResponseEntity<>(programs,HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ProgramDto> addProgram(@RequestBody ProgramDto program) {
		ProgramDto newProgram = programService.addProgram(program);
		if(newProgram == null)
			return ResponseEntity.badRequest().build();
		else
			return new ResponseEntity<>(newProgram,HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ProgramDto> updateProgram(@PathVariable Integer id, @RequestBody ProgramDto programDto) {
		ProgramDto updateProgram = programService.updateProgram(id, programDto);
		if (updateProgram == null)
			return ResponseEntity.notFound().build();
		else
			return new ResponseEntity<>(updateProgram, HttpStatus.OK);
	}
	
	@PutMapping("/deactivate/{id}")
	public ResponseEntity<?> deactivateProgram(@PathVariable("id") Integer id)  {
		programService.deactivateProgram(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProgram(@PathVariable("id") Integer id) {
		programService.deleteProgram(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
