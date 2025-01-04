package com.springboot.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

	List<Attribute> findByCategoryId(Integer categoryId);

}
