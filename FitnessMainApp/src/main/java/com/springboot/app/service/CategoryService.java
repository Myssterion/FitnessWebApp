package com.springboot.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.dto.CategoryDto;
import com.springboot.app.model.Category;
import com.springboot.app.repository.CategoryRepository;
import com.springboot.app.utility.EntityToDto;

@Service
@Transactional
public class CategoryService {

private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public Category findById(Integer id) {
		Category category = categoryRepository.findById(id).orElse(null);
		return category;
	}
	
	public List<CategoryDto> findAllCategories(){
		List<Category> categories =  categoryRepository.findAll();
		return categories.stream()
							.map(EntityToDto::ConvertToDto)
							.collect(Collectors.toList());
	}
	
	public List<CategoryDto> findAllCategoriesWith(String search,int limit){
		List<Category> categories =  categoryRepository.findByCategoryNameContainingIgnoreCase(search);
		return categories.stream()
							.limit(limit)
							.map(EntityToDto::ConvertToDto)
							.collect(Collectors.toList());
	}

}
