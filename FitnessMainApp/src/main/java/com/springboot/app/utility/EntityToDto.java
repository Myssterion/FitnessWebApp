package com.springboot.app.utility;

import com.springboot.app.dto.*;
import com.springboot.app.model.*;

import static com.springboot.app.utility.LoadData.GetPicturesURLs;

public class EntityToDto {
	
	public static ProgramBasicDto ConvertToDtoBasic(Program program){
		ProgramBasicDto programDto = new ProgramBasicDto();
		
		programDto.setId(program.getId());
		programDto.setName(program.getProgramName());
		
		return programDto;
	}

	
	public static ProgramInfoDto ConvertToDtoInfo(Program program){
		ProgramInfoDto programDto = new ProgramInfoDto();
		
		programDto.setId(program.getId());
		programDto.setName(program.getProgramName());
		programDto.setPrice(program.getPrice());
		programDto.setPictures(GetPicturesURLs(program.getPictures()));
		
		return programDto;
	}
	
	public static ProgramInfoDto ConvertToDtoInfo(RegularUserProgram program){
		ProgramInfoDto programDto = new ProgramInfoDto();
		
		programDto.setId(program.getId().getProgramId());
		programDto.setName(program.getProgram().getProgramName());
		programDto.setPrice(program.getProgram().getPrice());
		
		return programDto;
	}

	public static ProgramDto ConvertToDto(Program program){
		ProgramDto programDto = new ProgramDto();
		
		programDto.setId(program.getId());
		programDto.setName(program.getProgramName());
		programDto.setDuration(program.getDuration());
		programDto.setLocation(program.getLocation());
		programDto.setDescription(program.getDescription());
		programDto.setPrice(program.getPrice());
		programDto.setVideo(program.getVideo());
		if(program.getPictures() != null)
			programDto.setPictures(GetPicturesURLs(program.getPictures()));
		programDto.setCategory(ConvertToDto(program.getCategory()));
		programDto.setDifficulty(ConvertToDto(program.getDifficulty()));
		programDto.setAttribute(ConvertToDto(program.getAttribute()));
		programDto.setInstructor(ConvertToDtoInfo(program.getRegularUser()));
		
		return programDto;
	}
	
	public static CategoryDto ConvertToDto(Category category) {
		CategoryDto categoryDto = new CategoryDto();
		
		categoryDto.setId(category.getId());
		categoryDto.setCategoryName(category.getCategoryName());
		
		return categoryDto;
	}

	public static DifficultyDto ConvertToDto(Difficulty difficulty) {
		DifficultyDto difficultyDto = new DifficultyDto();
		
		difficultyDto.setId(difficulty.getId());
		difficultyDto.setDifficulty(difficulty.getDifficulty());
		
		return difficultyDto;
	}
	
	public static AttributeDto ConvertToDto(Attribute attribute) {
		AttributeDto attributeDto = new AttributeDto();
		
		attributeDto.setId(attribute.getId());
		attributeDto.setAttributeName(attribute.getAttributeName());
		
		return attributeDto;
	}
	
	public static CommentDto ConvertToDto(Comment comment) {
		CommentDto commentDto = new CommentDto();
		
		commentDto.setContent(comment.getContent());
		commentDto.setPosted(comment.getPosted().getTime());
		commentDto.setUsername(comment.getRegularUser().getUsername());
		
		return commentDto;
	}
	
	public static MessageDto ConvertToDto(Message message) {
		MessageDto messageDto = new MessageDto();
		
		messageDto.setContent(message.getContent());
		messageDto.setSentAt(message.getSentAt().getTime());
		messageDto.setSenderId(message.getUser1().getId());
		if(message.getUser2() == null) {
			messageDto.setReceiverUsername("");
			messageDto.setReceiverId(-1);
		} else {
			messageDto.setReceiverUsername(message.getUser2().getUsername());
			messageDto.setReceiverId(message.getUser2().getId());
		}
		return messageDto;
	}
	
	public static MessageDto ConvertToDtoFixedSender(Message message, Integer id) {
		MessageDto messageDto = new MessageDto();
		
		messageDto.setContent(message.getContent());
		messageDto.setSentAt(message.getSentAt().getTime());
		messageDto.setSenderId(id);
		if(message.getUser1().getId() == id ) {
			messageDto.setReceiverId(message.getUser2().getId());
			messageDto.setReceiverUsername(message.getUser2().getUsername());
		}	else if(message.getUser2().getId() == id ) {
			messageDto.setReceiverId(message.getUser1().getId());
			messageDto.setReceiverUsername(message.getUser1().getUsername());
		}
		return messageDto;
	}
	
	public static PictureDto ConvertToDto(Picture picture) {
		PictureDto pictureDto = new PictureDto();
		
		pictureDto.setId(picture.getId());
		pictureDto.setFileName(picture.getFileName());
		pictureDto.setFileType(picture.getFileType());
		pictureDto.setMimeType(picture.getMimeType());
		pictureDto.loadData();
		
		return pictureDto;
	}
	
	public static ActivityLogEntryDto ConvertToDto(ActivityLogEntry activityLogEntry) {
		ActivityLogEntryDto activityLogEntryDto = new ActivityLogEntryDto();
		
		activityLogEntryDto.setDate(activityLogEntry.getDate());
		activityLogEntryDto.setExercise(ConvertToDto(activityLogEntry.getExercis()));
		activityLogEntryDto.setDuration(activityLogEntry.getDuration());
		activityLogEntryDto.setWeight(activityLogEntry.getWeight());
		activityLogEntryDto.setIntensity(ConvertToDto(activityLogEntry.getIntensity()));
		
		return activityLogEntryDto;
	}
	
	public static IntensityDto ConvertToDto(Intensity intensity) {
		IntensityDto intensityDto = new IntensityDto();
		
		intensityDto.setId(intensity.getId());
		intensityDto.setIntensityName(intensity.getIntensityName());
		
		return intensityDto;
	}
	
	public static PaymentDto ConvertToDto(Payment payment) {
		PaymentDto paymentDto = new PaymentDto();
		
		paymentDto.setId(payment.getId());
		paymentDto.setMethod(payment.getMethod());
		
		return paymentDto;
	}

	public static ExerciseDto ConvertToDto(Exercise exercise) {
		ExerciseDto exerciseDto = new ExerciseDto();
		
		exerciseDto.setId(exercise.getId());
		exerciseDto.setExerciseName(exercise.getExerciseName());
		exerciseDto.setMuscle(exercise.getMuscle());
		exerciseDto.setType(exercise.getType());
		
		return exerciseDto;
	}
	
	public static RegularUserBasicDto ConvertToDtoInfo(RegularUser regularUser){
		RegularUserBasicDto basicDto = new RegularUserBasicDto();
		
		basicDto.setId(regularUser.getId());
		basicDto.setName(regularUser.getName());
		basicDto.setSurname(regularUser.getSurname());
		
		return basicDto;
	}
	
	public static RegularUserDto ConvertToDto(RegularUser regularUser){
		RegularUserDto regularUserDto = new RegularUserDto();
		
		regularUserDto.setId(regularUser.getId());
		regularUserDto.setName(regularUser.getName());
		regularUserDto.setSurname(regularUser.getSurname());
		regularUserDto.setUsername(regularUser.getUsername());
		regularUserDto.setPassword(regularUser.getPassword());
		regularUserDto.setCity(regularUser.getCity());
		regularUserDto.setEmail(regularUser.getEmail());
		
		return regularUserDto;
	}

	public static AvatarDto ConvertToDto(Avatar avatar) {
		AvatarDto avatarDto = new AvatarDto();
		
		avatarDto.setId(avatar.getId());
		avatarDto.setFileName(avatar.getFileName());
		avatarDto.setMimeType(avatar.getMimeType());
		avatarDto.setFilePath(avatar.getFilePath());
		avatarDto.loadData();
		
		return avatarDto;
	}
	
}
