package com.accountmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.accountmanagementsystem")
public class AccountManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagementSystemApplication.class, args);
	}
}