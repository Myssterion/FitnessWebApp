package model;

import java.io.Serializable;

public class Exercise implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3725102659456207234L;

	private int id;
	private String exerciseName;
	private String type;
	private String muscle;
	
	public Exercise() {
		// TODO Auto-generated constructor stub
	}

	public Exercise(int id) {
		super();
		this.id = id;
	}

	public Exercise(String exerciseName, String type, String muscle) {
		super();
		this.exerciseName = exerciseName;
		this.type = type;
		this.muscle = muscle;
	}
	
	public Exercise(int id, String exerciseName, String type, String muscle) {
		super();
		this.id = id;
		this.exerciseName = exerciseName;
		this.type = type;
		this.muscle = muscle;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMuscle() {
		return muscle;
	}

	public void setMuscle(String muscle) {
		this.muscle = muscle;
	}

}
