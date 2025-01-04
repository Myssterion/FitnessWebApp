package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Advisor;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Integer> {

}
