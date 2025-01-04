package com.springboot.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.RegularUser;

@Repository
public interface RegularUserRepository extends JpaRepository<RegularUser, Integer> {
	
	Optional<RegularUser> findByUsername(String username);
	
	Optional<RegularUser> findByVerificationToken(String username);

}
