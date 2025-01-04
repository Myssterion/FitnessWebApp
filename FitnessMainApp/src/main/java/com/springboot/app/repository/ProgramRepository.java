package com.springboot.app.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer>,  JpaSpecificationExecutor<Program> {
	
	List<Program> findByDeletedFalse();

	List<Program> findByProgramNameContainingIgnoreCaseAndDeletedFalseAndStatusTrue(String searchString);

	List<Program> findByCategoryIdAndDeletedFalseAndStatusTrue(Integer id);
	
	List<Program> findByRegularUserIdAndDeletedFalseAndStatusFalse(Integer id);
	
	List<Program> findByRegularUserIdAndDeletedFalseAndStatusTrue(Integer id);

	List<Program> findByAttributeIdAndDeletedFalseAndStatusTrue(Integer id);

	List<Program> findByDeletedFalseAndStatusTrue();

	@Query("SELECT p FROM Program p WHERE p.created = :createdAt AND p.deleted = false AND p.status = true")
	List<Program> findAllNewPrograms(@Param("createdAt") LocalDate createdAt);

	Optional<Program> findByIdAndDeletedFalseAndStatusTrue(Integer id);
	
}
