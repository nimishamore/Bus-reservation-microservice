package com.userservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServiceController {
	
	@GetMapping("/users")
	public String getUsers() {
		return "u1,u2";
	}

}
