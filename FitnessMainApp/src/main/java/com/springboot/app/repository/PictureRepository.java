package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Picture;
import com.springboot.app.model.Program;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
	
	List<Picture> findByProgram(Program program);

}
