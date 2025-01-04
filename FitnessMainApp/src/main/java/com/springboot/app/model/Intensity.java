package com.springboot.app.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the intensity database table.
 * 
 */
@Entity
public class Intensity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="intensity_name")
	private String intensityName;

	public Intensity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntensityName() {
		return this.intensityName;
	}

	public void setIntensityName(String intensityName) {
		this.intensityName = intensityName;
	}

}