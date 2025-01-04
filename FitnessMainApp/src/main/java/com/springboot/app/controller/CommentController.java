package com.springboot.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.CommentDto;
import com.springboot.app.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping("/find/{programId}")
	public ResponseEntity<List<CommentDto>> getAllCommentsForProgram(@PathVariable Integer programId){
		List<CommentDto> comments = commentService.findAllCommentsForProgram(programId);
		return new ResponseEntity<>(comments,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto ){
		CommentDto newComment = commentService.addComment(commentDto);
		return new ResponseEntity<>(newComment,HttpStatus.CREATED);
	}
}