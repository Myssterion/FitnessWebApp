package com.springboot.app.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.app.dto.AvatarDto;
import com.springboot.app.service.AvatarService;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

	private AvatarService avatarService;

	public AvatarController(AvatarService avatarService) {
		this.avatarService = avatarService;
	}

	@GetMapping("/find/{userId}")
	public ResponseEntity<Resource> getAvatarByUserId(@PathVariable Integer userId) {
		AvatarDto avatarDto = avatarService.findByIdFromUser(userId);
	
		if (avatarDto == null)
			return ResponseEntity.noContent().build();
		else
		{
			ByteArrayResource resource = new ByteArrayResource(avatarDto.getAvatarData());

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_JPEG);

	        return ResponseEntity.ok()
	                .headers(headers)
	                .body(resource);
		}
	}

	@PostMapping("/update/{userId}")
	public ResponseEntity<AvatarDto> update(@PathVariable Integer userId,
			@RequestParam("avatar") MultipartFile avatar) {

		AvatarDto avatarDto = avatarService.update(userId, avatar);
		if (avatarDto == null)
			return ResponseEntity.badRequest().build();
		else
			return new ResponseEntity<>(avatarDto, HttpStatus.OK);
	}

}
