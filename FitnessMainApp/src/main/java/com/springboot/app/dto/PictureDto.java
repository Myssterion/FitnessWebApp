package com.springboot.app.dto;

import com.springboot.app.utility.LoadData;

public class PictureDto {
	
	private int id;
	private String fileName;
	private String fileType;
	private String mimeType;
	private byte[] pictureData;
	
	public PictureDto() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public void loadData() {
		if(fileName.isBlank() || fileType.isBlank())
			return;
		pictureData = LoadData.GetPictureData(fileName, "PICTURE");
	}

	public byte[] getPictureData() {
		return pictureData;
	}

	public void setPictureData(byte[] pictureData) {
		this.pictureData = pictureData;
	}
	
	
}
