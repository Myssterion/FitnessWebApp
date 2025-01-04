package model;

import java.io.Serializable;

public class Difficulty implements Serializable{

	private static final long serialVersionUID = 464174089274491409L;

	private int id;
	private String difficulty;

	public Difficulty() {
		super();
	}

	public Difficulty(int id) {
		super();
		this.id = id;
	}

	public Difficulty(String difficulty) {
		super();
		this.difficulty = difficulty;
	}

	public Difficulty(int id, String difficulty) {
		super();
		this.id = id;
		this.difficulty = difficulty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
}
