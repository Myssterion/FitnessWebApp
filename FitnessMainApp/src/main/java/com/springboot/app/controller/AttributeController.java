package com.springboot.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.AttributeDto;
import com.springboot.app.service.AttributeService;

@RestController
@RequestMapping("/attribute")
public class AttributeController {
	
	private final AttributeService attributeService;

	public AttributeController(AttributeService attributeService) {
		this.attributeService = attributeService;
	}

	@GetMapping("/find/{categoryId}")
	public ResponseEntity<List<AttributeDto>> getAllAttributesForCategory(@PathVariable Integer categoryId){
		List<AttributeDto> attributes = attributeService.findAllAttributesForCategory(categoryId);
		return new ResponseEntity<>(attributes,HttpStatus.OK);
	}

}