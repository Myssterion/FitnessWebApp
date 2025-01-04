package com.springboot.app.model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the pictures database table.
 * 
 */
@Entity
@Table(name="pictures")
public class Picture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="file_name")
	private String fileName;

	@Column(name="file_type")
	private String fileType;

	@Column(name="mime_type")
	private String mimeType;

	//bi-directional many-to-one association to Program
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="program_id", nullable = false)
	private Program program;

	public Picture() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Program getProgram() {
		return this.program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

}