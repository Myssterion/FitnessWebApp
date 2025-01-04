package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Integer> {

}
