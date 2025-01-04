package com.springboot.app.dto;


public class CommentDto {

	private String content;

	private long posted;
	
	private Integer programId;//vjv cu morati promjeniti bar user da posalje i id
	
	private Integer userId;
	
	private String username;
	
	public CommentDto() {
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getPosted() {
		return posted;
	}

	public void setPosted(long posted) {
		this.posted = posted;
	}
	
	public Integer getProgramId() {
		return programId;
	}

	public void setProgramId(Integer programId) {
		this.programId = programId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
