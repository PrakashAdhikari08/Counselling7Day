package com.sevendays.counselling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class CounsellingbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CounsellingbackendApplication.class, args);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/home")

	public String index() {
		return "Online booking system of 7 days Counselling edited ";
	}
}
