package com.stayease.hostelapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@EnableJpaRepositories(basePackages = "com.stayease.hostelapp.repository")

public class HostelappApplication {

	public static void main(String[] args) {
		SpringApplication.run(HostelappApplication.class, args);
	}

}
