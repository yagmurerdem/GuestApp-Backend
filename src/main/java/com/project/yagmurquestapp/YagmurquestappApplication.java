package com.project.yagmurquestapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class YagmurquestappApplication {

	public static void main(String[] args) {
		SpringApplication.run(YagmurquestappApplication.class, args);
	}

}
