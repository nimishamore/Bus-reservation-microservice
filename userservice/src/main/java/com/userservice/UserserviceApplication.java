package com.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		if(args.length > 0) {
			System.setProperty("server.port", args[0]);
		}
		SpringApplication.run(UserserviceApplication.class, args);
		
	}

}
