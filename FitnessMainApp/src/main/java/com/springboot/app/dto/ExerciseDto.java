package com.springboot.app.dto;

public class ExerciseDto {

	private int id;
	
	private String exerciseName;

	private String muscle;

	private String type;

	
	public ExerciseDto() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getExerciseName() {
		return exerciseName;
	}


	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}


	public String getMuscle() {
		return muscle;
	}


	public void setMuscle(String muscle) {
		this.muscle = muscle;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return exerciseName + " | Muscle: " + muscle;
	}
	
	
}
