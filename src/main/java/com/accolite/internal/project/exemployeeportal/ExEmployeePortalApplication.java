package com.accolite.internal.project.exemployeeportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.accolite.internal.project.exemployeeportal.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class ExEmployeePortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExEmployeePortalApplication.class, args);
	}

}
