package com.springboot.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.springboot.app.model.Category;
import com.springboot.app.model.Program;

@Service
public class EmailService {
	
	private final JavaMailSender mailSender;
	private static final String SUBJECT = "REGISTRATION";
	private static final String SUBJECT_PROGRAMS = "New programs";
	private static final String TEXT = "You have registered successfully! To activate your account please click on the link: ";
	private static final String LINK = "http://localhost:8080/register/accountVerification/";

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	 public void sendMessage(String to, String token) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(SUBJECT);
	        message.setText(TEXT + LINK + token);
	        mailSender.send(message);
	 }

	public void sendPrograms(String to, Map<Category, List<Program>> programsForUser) {
		 	SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(SUBJECT_PROGRAMS);
	       
	        StringBuilder messageBody = new StringBuilder("Here are the new programs based on your subscriptions:\n\n");

	        for (Map.Entry<Category, List<Program>> entry : programsForUser.entrySet()) {
	            Category category = entry.getKey();
	            List<Program> programs = entry.getValue();

	            messageBody.append("Category: ").append(category.getCategoryName()).append("\n");
	            
	            for (Program program : programs) {
	                messageBody.append(" - ").append(program.getProgramName()).append("\n");
	            }

	            messageBody.append("\n"); 
	        }

	        message.setText(messageBody.toString());
	        
	        mailSender.send(message);
	}

}
