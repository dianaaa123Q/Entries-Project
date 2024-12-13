package com.example.Entries_Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EntriesProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntriesProjectApplication.class, args);
	}

}
