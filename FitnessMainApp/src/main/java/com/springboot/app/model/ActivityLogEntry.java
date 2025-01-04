package com.springboot.app.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the activity_log_entries database table.
 * 
 */
@Entity
@Table(name="activity_log_entries")
public class ActivityLogEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private int duration;

	private BigDecimal weight;

	//bi-directional many-to-one association to ActivityLog
	@ManyToOne
	@JoinColumn(name="activity_log_id")
	private ActivityLog activityLog;

	//bi-directional many-to-one association to Exercis
	@ManyToOne
	@JoinColumn(name="exercises_id")
	private Exercise exercis;

	//bi-directional many-to-one association to Intensity
	@ManyToOne
	@JoinColumn(name = "intensity_id")
	private Intensity intensity;

	public ActivityLogEntry() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public ActivityLog getActivityLog() {
		return this.activityLog;
	}

	public void setActivityLog(ActivityLog activityLog) {
		this.activityLog = activityLog;
	}

	public Exercise getExercis() {
		return this.exercis;
	}

	public void setExercis(Exercise exercis) {
		this.exercis = exercis;
	}

	public Intensity getIntensity() {
		return this.intensity;
	}

	public void setIntensity(Intensity intensity) {
		this.intensity = intensity;
	}

}