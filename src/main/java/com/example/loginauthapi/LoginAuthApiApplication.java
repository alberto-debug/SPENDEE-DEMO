package com.example.loginauthapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableScheduling
public class LoginAuthApiApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(LoginAuthApiApplication.class, args);
	}

}
