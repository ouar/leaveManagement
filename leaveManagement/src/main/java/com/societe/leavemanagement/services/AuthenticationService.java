package com.societe.leavemanagement.services;

import com.societe.leavemanagement.entities.Utilisateur;

/**
 * 
 * @author salah
 *
 */
public interface AuthenticationService {
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	String signin(String username, String password);

	/**
	 * 
	 * @param user
	 * @return
	 */
	String signup(Utilisateur user);

	/**
	 * 
	 * @param username
	 */
	void delete(String username);
}
