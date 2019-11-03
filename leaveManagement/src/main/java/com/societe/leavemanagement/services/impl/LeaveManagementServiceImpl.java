package com.societe.leavemanagement.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.societe.leavemanagement.entities.Collaborateur;
import com.societe.leavemanagement.repository.CollaborateurRepository;
import com.societe.leavemanagement.services.LeaveManagementService;

/**
 * 
 * @author salah
 *
 */
@Service
public class LeaveManagementServiceImpl implements LeaveManagementService {  

	/**
	 * 
	 */
	@Autowired
	CollaborateurRepository collaborateurRepository;
	/**
	 * 
	 */
	@Autowired
	private JmsTemplate jmsTopicTemplate;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void convertAndSend() {
		jmsTopicTemplate.convertAndSend("pubsub", "congé");
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void store(Collaborateur collaborateur) {
		collaborateurRepository.save(collaborateur);
		jmsTopicTemplate.convertAndSend("pubsub", "congé");
	}

}
