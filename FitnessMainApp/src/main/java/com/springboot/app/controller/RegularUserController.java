package com.springboot.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.CategoryDto;
import com.springboot.app.dto.RegularUserDto;
import com.springboot.app.service.RegularUserService;

@RestController
@RequestMapping("/regularUser")
public class RegularUserController {
	
	private final RegularUserService regularUserService;

	public RegularUserController(RegularUserService regularUserService) {
		this.regularUserService = regularUserService;
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<RegularUserDto> getRegularUserById(@PathVariable Integer id){
		RegularUserDto regUser = regularUserService.findById(id);
		if(regUser == null)
			return ResponseEntity.notFound().build();
		else
			return new ResponseEntity<>(regUser,HttpStatus.OK);
	}
	
	@GetMapping("/subscriptions/{id}")
	public ResponseEntity<List<CategoryDto>> getSubscriptions(@PathVariable Integer id){
		List<CategoryDto> categories = regularUserService.getSubscriptions(id);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@PostMapping("/subscribe/{userId}/{categoryId}")
	public ResponseEntity<?> subscribeToCategory(@PathVariable Integer userId, @PathVariable Integer categoryId){
		RegularUserDto regUser = regularUserService.subscribeToCategory(userId, categoryId);
		if(regUser != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/unsubscribe/{userId}/{categoryId}")
	public ResponseEntity<?> unsubscribeFromCategory(@PathVariable Integer userId, @PathVariable Integer categoryId){
		RegularUserDto regUser = regularUserService.unsubscribeFromCategory(userId, categoryId);
		if(regUser != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
    @PutMapping("/update/{id}")
    public ResponseEntity<RegularUserDto> update(@PathVariable Integer id, @RequestBody RegularUserDto regularUser){
    	RegularUserDto updatedUser = regularUserService.update(id, regularUser);
        if(updatedUser == null)
        	return ResponseEntity.notFound().build();
        else
        	return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    
    
}
