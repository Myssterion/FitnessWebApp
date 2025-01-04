package com.springboot.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.LoginDto;
import com.springboot.app.dto.RegularUserDto;
import com.springboot.app.service.RegularUserService;

@RestController
@RequestMapping("/login")
public class LoginController {

	private final RegularUserService regularUserService;

	public LoginController(RegularUserService regularUserService) {
			this.regularUserService = regularUserService;
	}
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
			RegularUserDto regularUserDto = regularUserService.loginUser(loginDto);
			if(regularUserDto != null) {
				if(regularUserDto.getId() != 0)
					return new ResponseEntity<>(regularUserDto, HttpStatus.OK);
				else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You need to activate your account through email!");
					
			}
			else 
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	}
}
