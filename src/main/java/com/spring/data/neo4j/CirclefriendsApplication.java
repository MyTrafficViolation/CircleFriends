package com.spring.data.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EntityScan("com.spring.data.neo4j.dto")
@EnableNeo4jRepositories(basePackages = "com.spring.data.neo4j.mapper")
public class CirclefriendsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CirclefriendsApplication.class, args);
	}
}
