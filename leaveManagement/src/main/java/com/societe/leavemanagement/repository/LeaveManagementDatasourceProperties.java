package com.societe.leavemanagement.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author salah
 *
 */
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

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}