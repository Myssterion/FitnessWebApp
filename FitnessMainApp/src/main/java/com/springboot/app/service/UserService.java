package com.springboot.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.model.User;
import com.springboot.app.repository.UserRepository;

@Service
@Transactional
public class UserService {

private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}

	public User findById(Integer id) {
		User user = userRepository.findById(id).orElse(null);
		return user;
	}

}
