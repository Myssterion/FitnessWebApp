package com.springboot.app.dto;

import java.math.BigDecimal;
import java.util.Set;

public class ProgramDto {

	public ProgramDto() {
		// TODO Auto-generated constructor stub
	}

	private int id;

	private String description;

	private int duration;

	private String location;

	private BigDecimal price;

	private String name;

	private boolean status;
	
	private String video;

	private Set<String> pictures; // promjeni za program da prima string

	private CategoryDto category;
	
	private AttributeDto attribute;

	private DifficultyDto difficulty;

	private RegularUserBasicDto instructor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public DifficultyDto getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(DifficultyDto difficulty) {
		this.difficulty = difficulty;
	}

	public RegularUserBasicDto getInstructor() {
		return instructor;
	}

	public void setInstructor(RegularUserBasicDto instructor) {
		this.instructor = instructor;
	}

	public Set<String> getPictures() {
		return pictures;
	}

	public void setPictures(Set<String> pictures) {
		this.pictures = pictures;
	}

	public AttributeDto getAttribute() {
		return attribute;
	}

	public void setAttribute(AttributeDto attribute) {
		this.attribute = attribute;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	@Override
	public String toString() {
		return "ProgramDto [id=" + id + ", description=" + description + ", duration=" + duration + ", location="
				+ location + ", price=" + price + ", name=" + name + ", status=" + status + ", pictures=" + pictures
				+ ", category=" + category + ", attribute=" + attribute + ", difficulty=" + difficulty + ", instructor=" + instructor + "]";
	}

}
