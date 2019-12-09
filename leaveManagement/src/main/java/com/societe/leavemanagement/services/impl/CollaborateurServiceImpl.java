package com.societe.leavemanagement.services.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.societe.leavemanagement.entities.Collaborateur;
import com.societe.leavemanagement.repository.CollaborateurRepository;
import com.societe.leavemanagement.services.CollaborateurService;

/**
 * 
 * @author salah
 *
 */
@Service
@Transactional(value = TxType.REQUIRES_NEW)
public class CollaborateurServiceImpl implements CollaborateurService {

	/**
	 * 
	 */
	@Autowired
	CollaborateurRepository collaborateurRepository;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void storeCollaborateur(Collaborateur collaborateur) {
		collaborateurRepository.save(collaborateur);

	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Collaborateur> getListCollaborateurs() {
		return collaborateurRepository.findAll();
	}

	

	@Override
	public void deleteCollaborateur(int idcollaborateur) {
		collaborateurRepository.deleteById(idcollaborateur);
	}

}
