package com.springboot.app.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the exercises database table.
 * 
 */
@Entity
@Table(name="exercises")
public class Exercise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="exercise_name")
	private String exerciseName;

	private String muscle;

	private String type;

	public Exercise() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExerciseName() {
		return this.exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public String getMuscle() {
		return this.muscle;
	}

	public void setMuscle(String muscle) {
		this.muscle = muscle;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
}