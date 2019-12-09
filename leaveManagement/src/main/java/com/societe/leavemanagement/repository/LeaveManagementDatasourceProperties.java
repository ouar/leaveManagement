package com.societe.leavemanagement.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author salah
 *
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "leavemanagement.datasource")
public class LeaveManagementDatasourceProperties {

	/*
	 * 
	 */
	private String url;
	/*
	 * 
	 */
	private String username;
	/*
	 * 
	 */
	private String password;

}