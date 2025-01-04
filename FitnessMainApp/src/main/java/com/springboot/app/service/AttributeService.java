package com.springboot.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.dto.AttributeDto;
import com.springboot.app.model.Attribute;
import com.springboot.app.repository.AttributeRepository;
import com.springboot.app.utility.EntityToDto;

@Service
@Transactional
public class AttributeService {

	private final AttributeRepository attributeRepository;
	
	public AttributeService(AttributeRepository attributeRepository) {
		this.attributeRepository = attributeRepository;
	}
	
	public List<Attribute> findAllAttributes(){
		return attributeRepository.findAll();
	}

	public List<AttributeDto> findAllAttributesForCategory(Integer categoryId) {
		List<Attribute> attributes = attributeRepository.findByCategoryId(categoryId);
		return attributes.stream()
						 .map(EntityToDto::ConvertToDto)
						 .collect(Collectors.toList());
	}

}
