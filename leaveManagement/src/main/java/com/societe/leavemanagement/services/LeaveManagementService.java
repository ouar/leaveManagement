package com.societe.leavemanagement.services;

/**
 * 
 */
import com.societe.leavemanagement.entities.Collaborateur;

/**
 * 
 * @author salah
 *
 */
public interface LeaveManagementService {
	/**
	 * 
	 * @param collaborateur 
	 */
	void store(Collaborateur collaborateur);

	/**
	 * 
	 */
	void convertAndSend();

}
