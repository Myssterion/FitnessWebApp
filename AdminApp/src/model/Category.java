package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable{

	private static final long serialVersionUID = 6029114789400431617L;

	private int id;
	private String name;
	private ArrayList<Attribute> attributes;

	public Category() {
		super();
	}
	
	public Category(int id) {
		this.id = id;
	}

	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Category(String name, ArrayList<Attribute> attributes) {
		this.name = name;
		this.attributes = attributes;
	}


	public Category(int id, String name, ArrayList<Attribute> attributes) {
		super();
		this.id = id;
		this.name = name;
		this.attributes = attributes;
	}

	public Category(String name) {
		super();
		this.name = name;
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

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}
	
	public ArrayList<Integer> getAttributesIDs() {
		ArrayList<Integer> attributesIDs = new ArrayList<>();
		for(var att : attributes)
			attributesIDs.add(att.getId()); 
		return attributesIDs;
	}

	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}

}
