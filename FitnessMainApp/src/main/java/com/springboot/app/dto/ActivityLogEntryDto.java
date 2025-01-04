package com.springboot.app.dto;

import java.math.BigDecimal;
import java.util.Date;


public class ActivityLogEntryDto {

	private Date date;
	private ExerciseDto exercise;
	private int duration;
	private BigDecimal weight;
	private IntensityDto intensity;
	
	public ActivityLogEntryDto() {
		// TODO Auto-generated constructor stub
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public ExerciseDto getExercise() {
		return exercise;
	}

	public void setExercise(ExerciseDto exercise) {
		this.exercise = exercise;
	}

	public IntensityDto getIntensity() {
		return intensity;
	}

	public void setIntensity(IntensityDto intensity) {
		this.intensity = intensity;
	}

}
