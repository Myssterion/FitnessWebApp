package com.springboot.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.RegularUserProgram;
import com.springboot.app.model.RegularUserProgramPK;

@Repository
public interface RegularUserProgramRepository extends JpaRepository<RegularUserProgram, RegularUserProgramPK> {

	List<RegularUserProgram> findByRegularUserId(Integer userId);

}
