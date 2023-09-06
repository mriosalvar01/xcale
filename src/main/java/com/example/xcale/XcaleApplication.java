package com.example.xcale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.xcale")
public class XcaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(XcaleApplication.class, args);
	}

}
