package com.example.InstalllmentSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class InstallmentSystemApplication {

	public static void main(String[] args) {
        SpringApplication.run(InstallmentSystemApplication.class, args);
	}

}
