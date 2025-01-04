package com.springboot.app.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.app.model.Picture;

public class LoadData {

	public static final String PICTURE_PATH = "resources/pictures/";
	public static final String AVATAR_PATH = "resources/avatars/";

	public static byte[] GetPictureData(String fileName, String picturePath) {
		try {
			Path filePath = null;
			if (picturePath.equals("AVATAR"))
				filePath = Paths.get(AVATAR_PATH + fileName);
			else if (picturePath.equals("PICTURE"))
				filePath = Paths.get(PICTURE_PATH + fileName);
			if (!Files.exists(filePath))
				return null;

			return Files.readAllBytes(filePath);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String GetPicturesURI(String name) {
		String path = "/pictures/" + name;

		return path;
	}

	public static Set<String> GetPicturesURLs(Set<Picture> pictures) {
																		
		Set<String> picturesURLs = new HashSet<>();
		for (var pic : pictures)
			picturesURLs.add("/pictures/" + pic.getFileName());
		return picturesURLs;
	}
	
	public static String GetPictureURL(Picture picture) {
		String pictureURL = "/pictures/" + picture.getFileName();
		return pictureURL;
}

	public static String UploadFile(MultipartFile file, String picturePath) {
		System.out.println(picturePath);
		String fileName = "";
		String directory = "";
		if(picturePath.equals("AVATAR")) {
			fileName = getUniqueName(AVATAR_PATH,file.getOriginalFilename());
			directory = AVATAR_PATH;
		}
		else if(picturePath.equals("PICTURE")) {
			fileName = getUniqueName(PICTURE_PATH,file.getOriginalFilename());
			directory = PICTURE_PATH;
		}
		
		if(fileName.equals(""))
			return null;

		try {
			Path path = Paths.get(directory + fileName);
			Files.write(path, file.getBytes(), StandardOpenOption.CREATE);
			return fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static String getUniqueName(String directory, String fileName) {
		File file = new File(directory, fileName);
		int count = 1;
		String uniqueName = "";
		
		if(file.exists()) {
		try {
			while (file.exists()) {
				uniqueName = GetFileNameWithoutExtension(fileName) + "(" + count + ")" + GetFileExtension(fileName);
				file = new File(directory, uniqueName);
				count++;
			}
			return uniqueName;
		} catch ( NullPointerException e) {
			e.printStackTrace();
		}
		}
		return fileName;
	}

	private static String GetFileNameWithoutExtension(String fullname) {
		if (fullname == null) {
			return null;
		}
		int dotIndex = fullname.lastIndexOf('.');
		return (dotIndex == -1) ? null : fullname.substring(0, dotIndex);
	}

	private static String GetFileExtension(String fileName) {
		if (fileName == null)
			return null;
		int dotIndex = fileName.lastIndexOf('.');
		return (dotIndex == -1) ? null : fileName.substring(dotIndex);
	}

	public static void DeleteAvatar(String fileName) {
		String fullPath = AVATAR_PATH + fileName;

		File file = new File(fullPath);
		if (file.exists())
			file.delete();
	}
	

	public static void DeletePicture(String fileName) {
		String fullPath = PICTURE_PATH + fileName;
		System.out.println(fileName);

		File file = new File(fullPath);
		if (file.exists())
			file.delete();
	}
	
}
