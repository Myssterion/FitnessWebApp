package com.springboot.app.service;


import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.dto.CategoryDto;
import com.springboot.app.dto.LoginDto;
import com.springboot.app.dto.RegisterDto;
import com.springboot.app.dto.RegularUserDto;
import com.springboot.app.model.ActivityLog;
import com.springboot.app.model.Avatar;
import com.springboot.app.model.Category;
import com.springboot.app.model.RegularUser;
import com.springboot.app.repository.RegularUserRepository;
import com.springboot.app.utility.EntityToDto;

import static com.springboot.app.utility.EntityToDto.*;
import static com.springboot.app.utility.DtoToEntity.DtoToRegularUser;

@Service
@Transactional
public class RegularUserService {

	private final RegularUserRepository regularUserRepository;
	private final AvatarService avatarService;
	private final ActivityLogService  activityLogService;
	private final CategoryService categoryService;
	private final EmailService emailService;
	
	public RegularUserService(RegularUserRepository regularUserRepository,AvatarService avatarService, 
			ActivityLogService  activityLogService, CategoryService categoryService,
			EmailService emailService) {
		this.regularUserRepository = regularUserRepository;
		this.avatarService = avatarService;
		this.activityLogService = activityLogService;
		this.categoryService = categoryService;
		this.emailService = emailService;
	}
	
	public List<RegularUserDto> findAllRegularUsers(){
		List<RegularUser> regularUsers = regularUserRepository.findAll();
		return regularUsers.stream()
							.map(EntityToDto::ConvertToDto)
							.collect(Collectors.toList());
	}
	
	public List<RegularUser> findAllUsers(){
		List<RegularUser> regularUsers = regularUserRepository.findAll();
		return regularUsers;
	}
	
	
	public RegularUserDto findById(Integer id){
		RegularUser user = findByIdRegularUser(id);
		if(user == null)
			return null;
		else
			return ConvertToDto(user);
	}
	
	public RegularUser findByUsername(String username) {
		RegularUser regularUser = regularUserRepository.findByUsername(username).orElse(null);
		return regularUser;
	}

	
	public RegularUser findByIdRegularUser(Integer id){
		RegularUser user = regularUserRepository.findById(id).orElse(null);
		return user;
	}
	
	
	public boolean isUsernameAvailable(String username) {
		RegularUser user = regularUserRepository.findByUsername(username).orElse(null);
		if(user != null)
			return false;
		return true;
	}

	public RegularUserDto registerUser(RegisterDto registerDto) {
		Avatar avatar = null;
		if(registerDto.getAvatar() != null)
			avatar = avatarService.add(registerDto.getAvatar());

		ActivityLog activityLog = activityLogService.create();
		RegularUser regularUser = DtoToRegularUser(registerDto);
		regularUser.setStatus(true);
		regularUser.setAvatar(avatar);
		regularUser.setActivityLog(activityLog);
		String token = generateVerificationToken();
		regularUser.setVerificationToken(token);
		regularUser.setTokenCreationTime(new Timestamp(System.currentTimeMillis()));

		
		emailService.sendMessage(registerDto.getEmail(), token);
		
		regularUserRepository.save(regularUser);
		
		return ConvertToDto(regularUser);
	}
	
	public RegularUserDto update(Integer id, RegularUserDto regularUserDto) {
		RegularUser oldUser = regularUserRepository.findById(id).orElse(null);
		if(oldUser == null || id != regularUserDto.getId())
			return null;
		
		oldUser.setName(regularUserDto.getName());
		oldUser.setSurname(regularUserDto.getSurname());
		oldUser.setEmail(regularUserDto.getEmail());
		oldUser.setCity(regularUserDto.getCity());
		oldUser.setPassword(regularUserDto.getPassword());;
		
		regularUserRepository.save(oldUser);
		
		return ConvertToDto(oldUser);
	}

	public RegularUserDto loginUser(LoginDto loginDto) {
		 RegularUser regularUser = regularUserRepository.findByUsername(loginDto.getUsername()).orElse(null);
	        if (regularUser != null && regularUser.getPassword().equals(loginDto.getPassword()) && regularUser.getStatus()) {
	        	if(regularUser.isVerified())
	        		return ConvertToDto(regularUser);
	        	else {
	        		String token = generateVerificationToken();
	        		regularUser.setVerificationToken(token);
	        		regularUser.setTokenCreationTime(new Timestamp(System.currentTimeMillis()));
	        		emailService.sendMessage(regularUser.getEmail(), token);
	        		
	        		regularUserRepository.save(regularUser);
	        		
	        		return new RegularUserDto();
	        	}
	        		
	        }
	        return null;
	}

	public RegularUserDto subscribeToCategory(Integer userId, Integer categoryId) {
		RegularUser regUser = findByIdRegularUser(userId);
		Category category = categoryService.findById(categoryId);
		if(regUser != null && category != null) {
			regUser.addCategory(category);
			return update(userId, ConvertToDto(regUser));
		}
		return null;
	}

	public RegularUserDto unsubscribeFromCategory(Integer userId, Integer categoryId) {
		RegularUser regUser = findByIdRegularUser(userId);
		Category category = categoryService.findById(categoryId);
		if(regUser != null && category != null) {
			regUser.removeCategory(category);
			return update(userId, ConvertToDto(regUser));
		}
		return null;
	}

	public List<CategoryDto> getSubscriptions(Integer id) {
		RegularUser regUser = findByIdRegularUser(id);
		if(regUser != null) {
			return regUser.getCategories().stream()
											.map(EntityToDto::ConvertToDto)
											.collect(Collectors.toList());
		}
		return null;
	}
	
	private String generateVerificationToken() {
		return UUID.randomUUID().toString();
	}

	public boolean verifyAccount(String token) {
		RegularUser regUser = regularUserRepository.findByVerificationToken(token).orElse(null);
		if(regUser != null) {
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			Timestamp creationTimestamp = regUser.getTokenCreationTime();
			
			Duration duration = Duration.between(currentTimestamp.toInstant(), creationTimestamp.toInstant());
			
			if(duration.toDays() <= 1) {
				regUser.setVerified(true);
				return regularUserRepository.save(regUser) != null;
			}
			
		}
		return false;
	}
}
