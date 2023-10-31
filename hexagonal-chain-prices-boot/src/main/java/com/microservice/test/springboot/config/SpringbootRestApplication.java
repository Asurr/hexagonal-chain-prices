package com.microservice.test.springboot.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.microservice.test"})
public class SpringbootRestApplication {


	public static void main(String[] args) {

		SpringApplication.run(SpringbootRestApplication.class, args);
	}



}
