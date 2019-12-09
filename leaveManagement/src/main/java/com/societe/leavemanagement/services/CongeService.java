package com.societe.leavemanagement.services;

import java.util.List;

import com.societe.leavemanagement.dto.CongeDataDTO;
import com.societe.leavemanagement.entities.Conge;

public interface CongeService {
	

	/**
	 * 
	 * @param userName
	 * @return
	 */
	List<CongeDataDTO> getListCongesCollaborateur(String userName);
	/**
	 * 
	 * @param idConge
	 * @param congeDto
	 */
	void updateCongeCollaborateur(int idConge, CongeDataDTO congeDto, String userName);
	/**
	 * 
	 * @param conge
	 */
	void storeCongeCollaborateur(Conge conge, String userName);
	/**
	 * 
	 * @param conge
	 */
	void deleteCongeCollaborateur(int idConge, String userName);	
	
}
