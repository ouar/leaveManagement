package com.societe.leavemanagement.services;

import java.util.List;

import com.societe.leavemanagement.entities.Collaborateur;

public interface CollaborateurService {	
	
	/**
	 * 
	 * @param collaborateur
	 */
	void storeCollaborateur(Collaborateur collaborateur);
	
	/**
	 * 
	 * @return
	 */
	List<Collaborateur> getListCollaborateurs();
	/**
	 * 
	 * @param idcollaborateur
	 */
	void deleteCollaborateur(int idcollaborateur);	
	
}
