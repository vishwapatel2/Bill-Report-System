package com.example.BillGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BillGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillGeneratorApplication.class, args);
	}

}
