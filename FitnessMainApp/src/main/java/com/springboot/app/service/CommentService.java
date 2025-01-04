package com.springboot.app.service;

import java.util.List;
import java.util.stream.Collectors;
import java.sql.Timestamp;
import java.util.Comparator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.dto.CommentDto;
import com.springboot.app.model.Comment;
import com.springboot.app.model.RegularUser;
import com.springboot.app.model.Program;
import com.springboot.app.repository.CommentRepository;
import com.springboot.app.utility.EntityToDto;

import static com.springboot.app.utility.EntityToDto.*;

@Service
@Transactional
public class CommentService {

	private final CommentRepository commentRepository;
	private final RegularUserService regularUserService;
	private final ProgramService programService;
	
	public CommentService(CommentRepository commentRepository, RegularUserService regularUserService, ProgramService programService) {
		this.commentRepository = commentRepository;
		this.regularUserService = regularUserService;
		this.programService = programService;
	}

	public List<CommentDto> findAllCommentsForProgram(Integer programId) {
		Program program = programService.findByIdProgram(programId);
		List<Comment> comments = commentRepository.findByProgram(program);
		if(comments.isEmpty()) 
			return null;
		
		List<CommentDto> commentsDto = comments.stream()
										.map(EntityToDto::ConvertToDto)
										.sorted(Comparator.comparing(CommentDto::getPosted).reversed())
										.collect(Collectors.toList());
		
		return commentsDto;
	}

	public CommentDto addComment(CommentDto commentDto) {
		
		RegularUser regUser = regularUserService.findByIdRegularUser(commentDto.getUserId());
		Program program = programService.findByIdProgram(commentDto.getProgramId());
		System.out.println(commentDto.getProgramId());
		System.out.println(commentDto.getUserId());
		if(regUser != null && program != null) {
			Comment comment = new Comment();
			comment.setContent(commentDto.getContent());
			comment.setPosted(new Timestamp(commentDto.getPosted()));
			comment.setRegularUser(regUser);
			comment.setProgram(program);
			return ConvertToDto(commentRepository.save(comment));
		}
		
		return null;
	}
}
