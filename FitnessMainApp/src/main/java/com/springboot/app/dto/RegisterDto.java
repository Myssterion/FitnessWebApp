package com.springboot.app.dto;

import org.springframework.web.multipart.MultipartFile;

public class RegisterDto {

	private String name;

	private String password;

	private String surname;

	private String username;
	
	private String city;

	private String email;
	
	private MultipartFile avatar;

	
	public RegisterDto() {
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public MultipartFile getAvatar() {
		return avatar;
	}


	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
	}
	
}
