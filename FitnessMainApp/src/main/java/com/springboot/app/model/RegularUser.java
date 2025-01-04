package com.springboot.app.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Set;
import java.sql.Timestamp;


/**
 * The persistent class for the regular_users database table.
 * 
 */
@Entity
@Table(name="regular_users")
@PrimaryKeyJoinColumn(name = "user_id")
public class RegularUser extends User implements Serializable {
	private static final long serialVersionUID = 1L;

/*	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int user_id;
	*/
	private String city;

	private String email;

	private boolean status;

	//bi-directional many-to-one association to ActivityLog
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "activity_log_id")
	private ActivityLog activityLog;
	
	@Column(name = "verification_token")
	private String verificationToken;
	
	@Column(name = "token_creation_time")
	private Timestamp tokenCreationTime;
	
	private boolean verified;
	/*//bi-directional many-to-one association to Program
	@OneToMany(mappedBy="regularUser", fetch = FetchType.LAZY)
	private Set<Program> programs;

	//bi-directional many-to-one association to RegularUserProgram
	@OneToMany(mappedBy="regularUser", fetch = FetchType.LAZY)
	private Set<RegularUserProgram> regularUserPrograms;
*/
	//bi-directional many-to-one association to Avatar
	@ManyToOne
	private Avatar avatar;

	//bi-directional many-to-many association to Category
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name="regular_user_subscriptions"
		, joinColumns={
			@JoinColumn(name="user_id", insertable=false, updatable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="category_id")
			}
		)
	private Set<Category> categories;

	public RegularUser() {
	}
/*
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
*/
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public ActivityLog getActivityLog() {
		return this.activityLog;
	}

	public void setActivityLog(ActivityLog activityLog) {
		this.activityLog = activityLog;
	}
	
	public String getVerificationToken() {
		return verificationToken;
	}
	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}
	public Timestamp getTokenCreationTime() {
		return tokenCreationTime;
	}
	public void setTokenCreationTime(Timestamp tokenCreationTime) {
		this.tokenCreationTime = tokenCreationTime;
	}
	
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	/*
	public Set<Program> getPrograms() {
		return this.programs;
	}

	public void setPrograms(Set<Program> programs) {
		this.programs = programs;
	}

	public Program addProgram(Program program) {
		getPrograms().add(program);
		program.setRegularUser(this);

		return program;
	}

	public Program removeProgram(Program program) {
		getPrograms().remove(program);
		program.setRegularUser(null);

		return program;
	}

	public Set<RegularUserProgram> getRegularUserPrograms() {
		return this.regularUserPrograms;
	}

	public void setRegularUserPrograms(Set<RegularUserProgram> regularUserPrograms) {
		this.regularUserPrograms = regularUserPrograms;
	}

	public RegularUserProgram addRegularUserProgram(RegularUserProgram regularUserProgram) {
		getRegularUserPrograms().add(regularUserProgram);
		regularUserProgram.setRegularUser(this);

		return regularUserProgram;
	}

	public RegularUserProgram removeRegularUserProgram(RegularUserProgram regularUserProgram) {
		getRegularUserPrograms().remove(regularUserProgram);
		regularUserProgram.setRegularUser(null);

		return regularUserProgram;
	}
*/
	public Avatar getAvatar() {
		return this.avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public Set<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	public void addCategory(Category category) {
		categories.add(category);
	}
	
	public void removeCategory(Category category) {
		if(categories.contains(category))
			categories.remove(category);
	}
}