package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Difficulty;

@Repository
public interface DifficultyRepository extends JpaRepository<Difficulty, Integer> {

}
