package com.springboot.app.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.ActivityLogEntryDto;
import com.springboot.app.dto.ExerciseDto;
import com.springboot.app.model.ActivityLog;
import com.springboot.app.service.ActivityLogService;

@RestController
@RequestMapping("/activityLog")
public class ActivityLogController {
	
	private final ActivityLogService activityLogService;

	public ActivityLogController(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}

	@GetMapping
	public ResponseEntity<List<ActivityLog>> getAllActivityLogs(){
		List<ActivityLog> activityLogs = activityLogService.findAllActivityLogs();
		return new ResponseEntity<>(activityLogs,HttpStatus.OK);
	}
	
	@GetMapping("/find/{userId}")
	public ResponseEntity<List<ActivityLogEntryDto>> getAllActivityLogsForUser(@PathVariable Integer userId){
		List<ActivityLogEntryDto> activityLogEntries = activityLogService.findAllActivityLogsForUser(userId);
		return new ResponseEntity<>(activityLogEntries,HttpStatus.OK);
	}
	
	@GetMapping("/find/exercise/{userId}")
	public ResponseEntity<List<ExerciseDto>> getAllExercisesForUser(@PathVariable Integer userId){
		List<ExerciseDto> exercisesDto = activityLogService.findAllExercisesForUser(userId);
		return new ResponseEntity<>(exercisesDto,HttpStatus.OK);
	}
	
	@GetMapping("/download/{userId}")
	public ResponseEntity<byte[]> getDiaryAsPDF(@PathVariable Integer userId) {
		byte[] pdfBytes = activityLogService.getDiaryAsPDF(userId);
		
		if(pdfBytes != null) {

         HttpHeaders headers = new HttpHeaders();
         headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=diary.pdf");
         headers.setContentType(MediaType.APPLICATION_PDF);

         return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
		}
		else
			return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/find/data/{userId}")
	public ResponseEntity<Map<Date, Double>> getAllDataForUser(@PathVariable Integer userId, @RequestParam Integer exerciseId, @RequestParam String period,  
			@RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month){
		Map<Date, Double> result = null;
		
		switch(period) {
		case "all": 
			result = activityLogService.findAllDataForUser(userId, exerciseId);
			break;
		case "year":
			result = activityLogService.findYearlyDataForUser(userId, exerciseId, year);
			break;
		case "month":
			result = activityLogService.findMonthlyDataForUser(userId, exerciseId, year, month);
			break;
		default:
			return ResponseEntity.badRequest().build();	
		}
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping("/add/{userId}")
	public ResponseEntity<ActivityLogEntryDto> addEntryToActivityLog(@PathVariable Integer userId, @RequestBody ActivityLogEntryDto activityLogEntryDto) {
		ActivityLogEntryDto newEntry = activityLogService.addActivityLogEntry(userId, activityLogEntryDto);
		if(newEntry == null) 
			return ResponseEntity.notFound().build();
		else
			return new ResponseEntity<>(newEntry,HttpStatus.OK);
	}
}