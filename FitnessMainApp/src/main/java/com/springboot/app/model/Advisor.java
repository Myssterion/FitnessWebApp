package com.springboot.app.model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the advisors database table.
 * 
 */
@Entity
@Table(name="advisors")
public class Advisor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int user_id;

	public Advisor() {
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}