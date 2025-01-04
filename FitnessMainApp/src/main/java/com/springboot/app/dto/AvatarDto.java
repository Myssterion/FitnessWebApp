package com.springboot.app.dto;

import com.springboot.app.utility.LoadData;

public class AvatarDto {

	private int id;
	private String fileName;
	private String filePath;
	private String mimeType;
	private byte[] avatarData;
	

	public AvatarDto() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public void loadData() {
		if(fileName.isBlank() || mimeType.isBlank())
			return;
		avatarData = LoadData.GetPictureData(fileName, "AVATAR");
	}

	public byte[] getAvatarData() {
		return avatarData;
	}

	public void setAvatarData(byte[] avatarData) {
		this.avatarData = avatarData;
	}
	
}
