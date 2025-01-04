package com.springboot.app.model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The primary key class for the regular_user_programs database table.
 * 
 */
@Embeddable
public class RegularUserProgramPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id", insertable=false, updatable=false)
	private int userId;

	@Column(name="program_id", insertable=false, updatable=false)
	private int programId;

	public RegularUserProgramPK() {
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProgramId() {
		return this.programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RegularUserProgramPK)) {
			return false;
		}
		RegularUserProgramPK castOther = (RegularUserProgramPK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.programId == castOther.programId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.programId;
		
		return hash;
	}
}