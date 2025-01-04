package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

}
