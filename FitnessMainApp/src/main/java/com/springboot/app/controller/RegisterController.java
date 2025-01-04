package com.springboot.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.RegisterDto;
import com.springboot.app.dto.RegularUserDto;
import com.springboot.app.service.RegularUserService;

@RestController
@RequestMapping("/register")
public class RegisterController {

	private final RegularUserService regularUserService;

	public RegisterController(RegularUserService regularUserService) {
			this.regularUserService = regularUserService;
	}
	
	@GetMapping("/checkUsername/{username}")
	public ResponseEntity<Boolean> checkIsUsernameAvailable(@PathVariable String username){//provjeriti mail je li unique
		if(regularUserService.isUsernameAvailable(username))
			return ResponseEntity.ok(true);
		else
			return ResponseEntity.ok(false);
	}
	
	@GetMapping("/accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token){//provjeriti mail je li unique
		if(regularUserService.verifyAccount(token))
			return ResponseEntity.ok("Your account has been successfully activated.");
		else
			return ResponseEntity.ok("Account activation failed. Invalid or expired token. Please login to get new token.");
	}
	
	@PostMapping
	public ResponseEntity<?> register(@ModelAttribute RegisterDto registerDto) {
			RegularUserDto regularUserDto = regularUserService.registerUser(registerDto);
			if(regularUserDto != null)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return ResponseEntity.badRequest().body("Registration failed!");
	}

}
