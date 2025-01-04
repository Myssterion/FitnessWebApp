package com.springboot.app.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.app.dto.AvatarDto;
import com.springboot.app.model.Avatar;
import com.springboot.app.model.RegularUser;
import com.springboot.app.repository.AvatarRepository;
import com.springboot.app.repository.RegularUserRepository;
import com.springboot.app.utility.LoadData;

import static com.springboot.app.utility.EntityToDto.*;

@Service
@Transactional
public class AvatarService {
	
	private final AvatarRepository avatarRepository;
	private final RegularUserRepository regularUserRepository;
	
	public AvatarService(AvatarRepository avatarRepository, RegularUserRepository regularUserRepository) {
		this.avatarRepository = avatarRepository;
		this.regularUserRepository = regularUserRepository;
	}
	
	public List<Avatar> findAllAvatars(){
		return avatarRepository.findAll();
	}
	
	public AvatarDto findByIdFromUser(Integer userId) {
		RegularUser regularUser = regularUserRepository.findById(userId).orElse(null);
		Avatar avatar = null;
	
		if(regularUser != null && regularUser.getAvatar() != null) {
			avatar = regularUser.getAvatar();
			return ConvertToDto(avatar);
		}
		
		return null;
	}

	public Avatar add(MultipartFile avatarFile) {
		System.out.println(avatarFile.getName());
		String fileName = LoadData.UploadFile(avatarFile,"AVATAR");
		if(fileName != null) {
			Avatar avatar = new Avatar();
			avatar.setFileName(fileName);
			avatar.setMimeType(avatarFile.getContentType());
			avatar.setFilePath(LoadData.AVATAR_PATH + fileName);
			return avatarRepository.save(avatar);
		}
		else
			return null;
	}

	public AvatarDto update(Integer userId, MultipartFile avatar) {
		if (avatar.isEmpty()) 
			return null;
		
		RegularUser regularUser = regularUserRepository.findById(userId).orElse(null);
		Avatar updateAvatar = null;
		
		if(regularUser != null)
			updateAvatar = regularUser.getAvatar();
		
		if(updateAvatar == null)
			return null;
		
		LoadData.DeleteAvatar(updateAvatar.getFileName());
		String fileName = LoadData.UploadFile(avatar,"AVATAR");
		
		if(fileName != null) {
			updateAvatar.setFileName(fileName);
			updateAvatar.setMimeType(avatar.getContentType());
			updateAvatar.setFilePath(LoadData.AVATAR_PATH + File.separator + avatar.getOriginalFilename());
			return ConvertToDto(avatarRepository.save(updateAvatar));
		} else
			return null;
	}
	
	
}
