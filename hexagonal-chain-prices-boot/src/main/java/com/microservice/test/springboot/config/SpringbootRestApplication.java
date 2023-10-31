package com.microservice.test.springboot.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages = {"com.microservice.test"})
@EnableJpaRepositories(basePackages={"com.microservice.test.infrastructure.h2.repository.brand.repository","com.microservice.test.infrastructure.h2.repository.price.repository"})
@EntityScan(basePackages={"com.microservice.test.infrastructure.h2.repository.brand.entity","com.microservice.test.infrastructure.h2.repository.price.entity"})
@EnableTransactionManagement
public class SpringbootRestApplication {


	public static void main(String[] args) {

		SpringApplication.run(SpringbootRestApplication.class, args);
	}



}
