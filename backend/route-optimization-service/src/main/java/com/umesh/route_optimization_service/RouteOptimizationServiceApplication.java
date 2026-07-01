package com.umesh.route_optimization_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RouteOptimizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouteOptimizationServiceApplication.class, args);
	}

}
