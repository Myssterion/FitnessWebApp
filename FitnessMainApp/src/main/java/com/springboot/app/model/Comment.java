package com.springboot.app.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the comments database table.
 * 
 */
@Entity
@Table(name="comments")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String content;

	private Timestamp posted;

	//bi-directional many-to-one association to Program
	@ManyToOne
	@JoinColumn(name="program_id")
	private Program program;

	//bi-directional many-to-one association to RegularUser
	@ManyToOne
	@JoinColumn(name="user_id")
	private RegularUser regularUser;

	public Comment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getPosted() {
		return this.posted;
	}

	public void setPosted(Timestamp posted) {
		this.posted = posted;
	}

	public Program getProgram() {
		return this.program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public RegularUser getRegularUser() {
		return this.regularUser;
	}

	public void setRegularUser(RegularUser regularUser) {
		this.regularUser = regularUser;
	}

}