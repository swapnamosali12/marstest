package com.example.marstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.example.marstest.dao"})
public class MarstestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarstestApplication.class, args);
	}

}
