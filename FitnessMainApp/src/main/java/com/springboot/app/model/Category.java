package com.springboot.app.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="category_name")
	private String categoryName;

	public Category() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}