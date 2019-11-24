package com.societe.leavemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class LeaveManagementApplication {

	public static void main(String[] args) {
		log.info("start");
		SpringApplication.run(LeaveManagementApplication.class, args);
	}

}
