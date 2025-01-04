package com.springboot.app.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the attributes database table.
 * 
 */
@Entity
@Table(name="attributes")
public class Attribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="attribute_name")
	private String attributeName;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Attribute() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}