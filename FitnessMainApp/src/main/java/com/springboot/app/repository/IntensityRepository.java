package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Intensity;

@Repository
public interface IntensityRepository extends JpaRepository<Intensity, Integer> {


}
