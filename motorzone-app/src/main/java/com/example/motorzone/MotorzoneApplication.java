package com.example.motorzone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MotorzoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotorzoneApplication.class, args);
	}

}
