package com.springboot.app.dto;

import java.math.BigDecimal;
import java.util.Set;

public class ProgramInfoDto {

	private int id;

	private BigDecimal price;

	private String name;
	
	private Set<String> pictures;
	
	public ProgramInfoDto() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getPictures() {
		return pictures;
	}

	public void setPictures(Set<String> pictures) {
		this.pictures = pictures;
	}

	
}
