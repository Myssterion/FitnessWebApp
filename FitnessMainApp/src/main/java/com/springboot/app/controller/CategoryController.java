package com.springboot.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.CategoryDto;
import com.springboot.app.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/find")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		List<CategoryDto> categories = categoryService.findAllCategories();
		return new ResponseEntity<>(categories,HttpStatus.OK);
	}
	
	@GetMapping("/seek")
	public ResponseEntity<List<CategoryDto>> getAllCategoriesWith(@RequestParam String search, @RequestParam (defaultValue = "5") int limit){
		List<CategoryDto> categories = categoryService.findAllCategoriesWith(search, limit);
		return new ResponseEntity<>(categories,HttpStatus.OK);
	}

}