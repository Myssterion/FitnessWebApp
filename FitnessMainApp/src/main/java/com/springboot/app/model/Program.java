package com.springboot.app.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


/**
 * The persistent class for the programs database table.
 * 
 */
@Entity
@Table(name="programs")
public class Program implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private boolean deleted;

	private String description;

	private int duration;

	private String location;

	private String video;
	
	private BigDecimal price;

	@Column(name="program_name")
	private String programName;

	private boolean status;
	
	@Column(name="created")
	private LocalDate created;

	//bi-directional many-to-one association to Picture
	@OneToMany(mappedBy="program", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Picture> pictures;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "attribute_id")
	private Attribute attribute;
	
	//bi-directional many-to-one association to Difficulty
	@ManyToOne
	@JoinColumn(name = "difficulty_id")
	private Difficulty difficulty;

	//bi-directional many-to-one association to RegularUser
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="instructor_id")
	private RegularUser regularUser;
/*
	//bi-directional many-to-one association to RegularUserProgram
	@OneToMany(mappedBy="program")
	private Set<RegularUserProgram> regularUserPrograms;
*/
	public Program() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<Picture> getPictures() {
		return this.pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	public Picture addPicture(Picture picture) {
		getPictures().add(picture);
		picture.setProgram(this);

		return picture;
	}

	public Picture removePicture(Picture picture) {
		getPictures().remove(picture);
		picture.setProgram(null);

		return picture;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public RegularUser getRegularUser() {
		return this.regularUser;
	}

	public void setRegularUser(RegularUser regularUser) {
		this.regularUser = regularUser;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}
}