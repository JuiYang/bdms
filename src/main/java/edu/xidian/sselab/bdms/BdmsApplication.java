package edu.xidian.sselab.bdms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class BdmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BdmsApplication.class, args);
	}
}
