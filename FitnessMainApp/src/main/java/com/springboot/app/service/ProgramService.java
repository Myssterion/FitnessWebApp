package com.springboot.app.service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.dto.FilterDto;
import com.springboot.app.dto.ProgramBasicDto;
import com.springboot.app.dto.ProgramDto;
import com.springboot.app.dto.ProgramInfoDto;
import com.springboot.app.model.Category;
import com.springboot.app.model.Program;
import com.springboot.app.model.Picture;
import com.springboot.app.repository.ProgramRepository;
import com.springboot.app.utility.EntityToDto;
import com.springboot.app.utility.ProgramSpecification;
import com.springboot.app.utility.LoadData;

import static com.springboot.app.utility.EntityToDto.*;
import static com.springboot.app.utility.DtoToEntity.*;

@Service
@Transactional
public class ProgramService {

	private final ProgramRepository programRepository;
	private final PictureService pictureService;
	
	public ProgramService(ProgramRepository programRepository, PictureService pictureService) {
		this.programRepository = programRepository;
		this.pictureService = pictureService;
	}
	
	public List<ProgramInfoDto> findAllPrograms(){
		List<Program> programs = programRepository.findByDeletedFalseAndStatusTrue();
		return programs.stream()
						.map(EntityToDto::ConvertToDtoInfo)
						.collect(Collectors.toList());
	}
	
	public List<ProgramBasicDto> findAllProgramsWith(String search, int limit) {
		List<Program> programs = programRepository.findByProgramNameContainingIgnoreCaseAndDeletedFalseAndStatusTrue(search);
		return programs.stream()
						.limit(limit)
						.map(EntityToDto::ConvertToDtoBasic)
						.collect(Collectors.toList());
	}
	
	public List<ProgramInfoDto> findAllProgramsWithCategory(Integer id) {
			List<Program> programs = programRepository.findByCategoryIdAndDeletedFalseAndStatusTrue(id);
			return programs.stream()
							.map(EntityToDto::ConvertToDtoInfo)
							.collect(Collectors.toList());
	}
	
	public List<ProgramInfoDto> findAllProgramsWithAttribute(Integer id) {
		List<Program> programs = programRepository.findByAttributeIdAndDeletedFalseAndStatusTrue(id);
		return programs.stream()
						.map(EntityToDto::ConvertToDtoInfo)
						.collect(Collectors.toList());
}
	
	public ProgramDto findById(Integer id) {
		Program program = findByIdProgram(id);
		if(program == null)
			return null;
		else
			return ConvertToDto(program);
	}
	
	public Program findByIdProgram(Integer id) {
		Program program = programRepository.findByIdAndDeletedFalseAndStatusTrue(id).orElse(null);
		return program;
	}
	
	 public List<ProgramInfoDto> getFilteredPrograms(FilterDto filterDTO) {
	        ProgramSpecification spec = new ProgramSpecification(filterDTO);
	    	List<Program> programs = programRepository.findAll(spec);
	    	return programs.stream()
	    			.filter(p -> p.getDeleted() == false && p.getStatus() == true)
					.map(EntityToDto::ConvertToDtoInfo)
					.collect(Collectors.toList());
	    }
	
	public ProgramDto addProgram(ProgramDto programDto) {
		Program program = DtoToProgram(programDto);
		program.setStatus(true);
		program.setCreated(LocalDate.now());
		programRepository.save(program);
		return ConvertToDto(program);
	}

	public ProgramDto updateProgram(Integer id, ProgramDto programDto) {
		Program oldProgram = programRepository.findById(id).orElse(null);
		if(oldProgram == null || id != programDto.getId())
			return null;
		checkPictures(oldProgram,programDto);
		oldProgram.setProgramName(programDto.getName());
		oldProgram.setDescription(programDto.getDescription());
		oldProgram.setDuration(programDto.getDuration());
		oldProgram.setLocation(programDto.getLocation());
		oldProgram.setPrice(programDto.getPrice());
		oldProgram.setVideo(programDto.getVideo());
		oldProgram.setAttribute(DtoToAttribute(programDto.getAttribute()));
		oldProgram.setCategory(DtoToCategory(programDto.getCategory()));
		oldProgram.setDifficulty(DtoToDifficulty(programDto.getDifficulty()));
		
		programRepository.save(oldProgram);
		
		return ConvertToDto(oldProgram);
	}

	private void checkPictures(Program oldProgram, ProgramDto programDto) {
	    Set<Picture> currentPics = oldProgram.getPictures();
	    Iterator<Picture> iterator = currentPics.iterator();

	    while (iterator.hasNext()) {
	        Picture pic = iterator.next();
	        System.out.println("CHECK PIC - " + pic.getFileName());
	        if (!programDto.getPictures().contains(LoadData.GetPictureURL(pic))) {           
	            iterator.remove();
	        	pictureService.removePicture(pic);
	        }
	    }
	}

	public boolean deleteProgram(Integer id) {
		Program program = programRepository.findById(id).orElse(null);
		if(program == null)
			return false;
		else {
			program.setDeleted(true);
			programRepository.save(program);
			return true;
		}
	}

	public List<ProgramInfoDto> findActiveForUser(Integer userId) {
		List<Program> programs = programRepository.findByRegularUserIdAndDeletedFalseAndStatusTrue(userId);
		return programs.stream()
						.map(EntityToDto::ConvertToDtoInfo)
						.collect(Collectors.toList());
	}

	public List<ProgramInfoDto> findInactiveForUser(Integer userId) {
		List<Program> programs = programRepository.findByRegularUserIdAndDeletedFalseAndStatusFalse(userId);
		return programs.stream()
						.map(EntityToDto::ConvertToDtoInfo)
						.collect(Collectors.toList());
	}

	public boolean deactivateProgram(Integer id) {
		Program program = programRepository.findById(id).orElse(null);
		
		if(program == null)
			return false;
		
		program.setStatus(false);
		programRepository.save(program);
		return true;
	}

	public Map<Category,List<Program>> findAllNewPrograms(LocalDate created) {
		List<Program> newPrograms = programRepository.findAllNewPrograms(created);
		if(newPrograms.isEmpty())
			return null;
		Map<Category,List<Program>> programsByCategory = newPrograms.stream()
															  .collect(Collectors.groupingBy(Program::getCategory));
		return programsByCategory;
	}

}
