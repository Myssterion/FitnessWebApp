package com.springboot.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.app.dto.PictureDto;
import com.springboot.app.service.PictureService;
import com.springboot.app.utility.LoadData;

@RestController
@RequestMapping("/picture")
public class PictureController {
	
	private final PictureService pictureService;

	public PictureController(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<String>> getAllPictures(@PathVariable Integer id){
		List<PictureDto> pictures = pictureService.findAllPictures(id);
		List<String> picturesURLs = new ArrayList<>();
		for(var p : pictures)
			picturesURLs.add(LoadData.GetPicturesURI(p.getFileName()));
		
		return ResponseEntity.ok(picturesURLs);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Resource> getPictureById(@PathVariable Integer id){
		PictureDto pictureDto = pictureService.findById(id);
		if(pictureDto == null)
			return ResponseEntity.notFound().build();
		else
		{
			ByteArrayResource resource = new ByteArrayResource(pictureDto.getPictureData());

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_PNG);

	        return ResponseEntity.ok()
	                .headers(headers)
	                .body(resource);
		}

	}
	
	@PostMapping("/upload/{programId}")
	public ResponseEntity<?> addPicturesForProgram(@PathVariable Integer programId, @RequestParam("pictures") MultipartFile[] pictures){
		boolean success = pictureService.uploadPictures(programId,pictures);
		if(success)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return ResponseEntity.badRequest().body("Registration failed!");
	}
}