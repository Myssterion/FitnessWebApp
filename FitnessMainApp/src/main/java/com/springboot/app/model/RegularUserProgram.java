package com.springboot.app.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the regular_user_programs database table.
 * 
 */
@Entity
@Table(name="regular_user_programs")
public class RegularUserProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RegularUserProgramPK id;

	//bi-directional many-to-one association to Payment
	@ManyToOne
	private Payment payment;
	
	 @ManyToOne
	    @MapsId("userId")
	    @JoinColumn(name = "user_id", insertable = false, updatable = false)
	    private RegularUser regularUser;

	    @ManyToOne
	    @MapsId("programId")
	    @JoinColumn(name = "program_id", insertable = false, updatable = false)
	    private Program program;

	public RegularUserProgram() {
	}

	public RegularUserProgramPK getId() {
		return this.id;
	}

	public void setId(RegularUserProgramPK id) {
		this.id = id;
	}

	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public RegularUser getRegularUser() {
		return regularUser;
	}

	public void setRegularUser(RegularUser regularUser) {
		this.regularUser = regularUser;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	
}