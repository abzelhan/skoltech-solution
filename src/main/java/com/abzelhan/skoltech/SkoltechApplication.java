package com.abzelhan.skoltech;

import com.abzelhan.skoltech.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class})
@SpringBootApplication
public class SkoltechApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkoltechApplication.class, args);
	}

}
