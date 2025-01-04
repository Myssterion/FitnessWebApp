package com.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FitnessAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessAppApplication.class, args);
	}

}
