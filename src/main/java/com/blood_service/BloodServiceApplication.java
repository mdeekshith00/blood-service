package com.blood_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "com.blood_service.entities")
//@ComponentScan(basePackages = "com.blood_service")
@EnableTransactionManagement
@EnableDiscoveryClient
public class BloodServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodServiceApplication.class, args);
	}

}
