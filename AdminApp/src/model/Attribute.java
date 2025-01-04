package model;

import java.io.Serializable;

public class Attribute implements Serializable{

	private static final long serialVersionUID = -210908387791221115L;

	private int id;
	private String name;
	private Category category;

	public Attribute() {
		super();
	}

	public Attribute(int attributeID) {
		this.id = attributeID;
	}
	
	public Attribute(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	
	public Attribute(int id, String name, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
	}

	public Attribute(String name, Category category) {
		super();
		this.name = name;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
