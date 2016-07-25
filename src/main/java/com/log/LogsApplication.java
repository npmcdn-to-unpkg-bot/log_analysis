package com.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.log")
public class LogsApplication
{

	public static void main(String[] args) {
		SpringApplication.run(LogsApplication.class, args);
	}
}
