package com.societe.leavemanagement.dto;

import java.io.Serializable;
import java.util.List;

import com.societe.leavemanagement.entities.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author salah
 *
 */
@Getter
@Setter
@ToString
public class UserDataDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String userName;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private List<Role> roles;
	/**
	 * 
	 */
	private String token;
}
