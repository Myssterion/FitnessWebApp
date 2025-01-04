package com.springboot.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Comment;
import com.springboot.app.model.Program;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findByProgram(Program program);
}
