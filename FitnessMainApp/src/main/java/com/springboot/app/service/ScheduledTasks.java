package com.springboot.app.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.model.Category;
import com.springboot.app.model.Program;
import com.springboot.app.model.RegularUser;

@Component
public class ScheduledTasks {
	
		private final RegularUserService regularUserService;
		private final ProgramService programService;
		private final EmailService emailService;

		public ScheduledTasks(RegularUserService regularUserService, ProgramService programService, EmailService emailService) {
			this.regularUserService = regularUserService;
			this.programService = programService;
			this.emailService = emailService;
		}

	 	@Scheduled(cron = "0 0 8 * * ?", zone = "Europe/Paris") // Executes at 8:00 AM every day
	 	@Transactional
	    public void performDailyTask() {
	 		LocalDate yesterday = LocalDate.now().minusDays(1);
	        Map<Category, List<Program>> newPrograms = programService.findAllNewPrograms(yesterday);
	        if(newPrograms.isEmpty())
	        	return;
	        else {
	        	List<RegularUser> users = regularUserService.findAllUsers();
	        	for(var user : users) 
	        		if(!user.getCategories().isEmpty()) {
	        			Map<Category, List<Program>> programsForUser = new HashMap<>();
	        			for(var cat : user.getCategories()) 
	        				programsForUser.put(cat, newPrograms.get(cat));
	        			
	        			emailService.sendPrograms(user.getEmail(), programsForUser);
	        		}
	        }
	    }
}
