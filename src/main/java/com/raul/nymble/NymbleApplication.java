package com.raul.nymble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class NymbleApplication {

	public static void main(String[] args) {
		SpringApplication.run(NymbleApplication.class, args);
	}

}
