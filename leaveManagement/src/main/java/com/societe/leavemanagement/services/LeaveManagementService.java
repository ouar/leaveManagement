package com.societe.leavemanagement.services;

import java.util.List;

import com.societe.leavemanagement.dto.CongeDataDTO;
import com.societe.leavemanagement.entities.Collaborateur;
import com.societe.leavemanagement.entities.Conge;

public interface LeaveManagementService {

	/**
	 * 
	 * @param collaborateur
	 */
	void store(Collaborateur collaborateur);

	/**
	 * 
	 * @param userName
	 * @return
	 */
	List<CongeDataDTO> getListCongesCollaborateur(String userName);
	
	/**
	 * 
	 * @return
	 */
	List<Collaborateur> getListCollaborateur();
	

	/**
	 * 
	 * @param conge
	 */
	void store(Conge conge);
	/**
	 * 
	 * @param conge
	 */
	void delete(Conge conge);

	/**
	 * 
	 * @param idcollaborateur
	 */
	void deleteCollaborateur(int idcollaborateur);


}
