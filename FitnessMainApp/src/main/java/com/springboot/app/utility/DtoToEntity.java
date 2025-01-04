package com.springboot.app.utility;

import com.springboot.app.dto.*;
import com.springboot.app.model.*;

public class DtoToEntity {

	public static Program DtoToProgram(ProgramDto programDto) {
		Program program = new Program();
		
		program.setProgramName(programDto.getName());
		program.setDescription(programDto.getDescription());
		program.setDuration(programDto.getDuration());
		program.setLocation(programDto.getLocation());
		program.setPrice(programDto.getPrice());
		program.setVideo(programDto.getVideo());
		program.setCategory(DtoToCategory(programDto.getCategory()));
		program.setAttribute(DtoToAttribute(programDto.getAttribute()));
		program.setDifficulty(DtoToDifficulty(programDto.getDifficulty()));
		program.setRegularUser(DtoToRegularUserBasic(programDto.getInstructor()));
		
		return program;
		
	}
	
	public static Attribute DtoToAttribute(AttributeDto attributeDto) {
		Attribute attribute = new Attribute();
		
		attribute.setId(attributeDto.getId());
		attribute.setAttributeName(attributeDto.getAttributeName());
		
		return attribute;
	}

	public static ActivityLogEntry DtoToActivityLogEntry(ActivityLogEntryDto activityLogEntryDto) {
		ActivityLogEntry activityLogEntry = new ActivityLogEntry();
		
		activityLogEntry.setDate(activityLogEntryDto.getDate());
		activityLogEntry.setExercis(DtoToExercise(activityLogEntryDto.getExercise()));
		activityLogEntry.setDuration(activityLogEntryDto.getDuration());
		activityLogEntry.setWeight(activityLogEntryDto.getWeight());
		activityLogEntry.setIntensity(DtoToIntensity(activityLogEntryDto.getIntensity()));
		
		return activityLogEntry;
		
	}
	
	public static Category DtoToCategory(CategoryDto categoryDto) {
		Category category = new Category();
		
		category.setId(categoryDto.getId());
		category.setCategoryName(categoryDto.getCategoryName());
		
		return category;
		
	}
	
	public static Difficulty DtoToDifficulty(DifficultyDto difficultyDto) {
		Difficulty difficulty = new Difficulty();
		
		difficulty.setId(difficultyDto.getId());
		difficulty.setDifficulty(difficultyDto.getDifficulty());
		
		return difficulty;
		
	}
	
	public static Intensity DtoToIntensity(IntensityDto intensityDto) {
		Intensity intensity = new Intensity();
		
		intensity.setId(intensityDto.getId());
		intensity.setIntensityName(intensityDto.getIntensityName());
		
		return intensity;
		
	}
	
	public static Exercise DtoToExercise(ExerciseDto exerciseDto) {
		Exercise exercise = new Exercise();
		
		exercise.setId(exerciseDto.getId());
		exercise.setExerciseName(exerciseDto.getExerciseName());
		exercise.setMuscle(exerciseDto.getMuscle());
		exercise.setType(exerciseDto.getType());
		
		return exercise;
		
	}
	
	public static RegularUser DtoToRegularUserBasic(RegularUserBasicDto regularUserDto) {
		RegularUser regularUser = new RegularUser();
		
		regularUser.setId(regularUserDto.getId());
		regularUser.setName(regularUserDto.getName());
		regularUser.setSurname(regularUserDto.getSurname());
		
		return regularUser;
		
	}
	
	public static RegularUser DtoToRegularUser(RegisterDto registerDto) {
		RegularUser regularUser = new RegularUser();
		
		regularUser.setName(registerDto.getName());
		regularUser.setSurname(registerDto.getSurname());
		regularUser.setUsername(registerDto.getUsername());
		regularUser.setEmail(registerDto.getEmail());
		regularUser.setCity(registerDto.getCity());
		regularUser.setPassword(registerDto.getPassword());
		
		return regularUser;
	}
}
