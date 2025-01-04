package com.springboot.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.model.ActivityLogEntry;
import com.springboot.app.service.ActivityLogEntryService;

@RestController
@RequestMapping("/activityLogEntry")
public class ActivityLogEntryController {
	
	private final ActivityLogEntryService activityLogEntryService;

	public ActivityLogEntryController(ActivityLogEntryService activityLogEntryService) {
		this.activityLogEntryService = activityLogEntryService;
	}

	@GetMapping
	public ResponseEntity<List<ActivityLogEntry>> getAllActivityLogEntries(){
		List<ActivityLogEntry> activityLogEntrys = activityLogEntryService.findAllActivityLogEntrys();
		return new ResponseEntity<>(activityLogEntrys,HttpStatus.OK);
	}

}