package model;

import java.io.Serializable;

public class Intensity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1698913932907556111L;

	private int id;
	private String intensity;
	
	public Intensity(String intensity) {
		super();
		this.intensity = intensity;
	}

	public Intensity(int id) {
		super();
		this.id = id;
	}

	public Intensity(int id, String intensity) {
		super();
		this.id = id;
		this.intensity = intensity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntensity() {
		return intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}
}
