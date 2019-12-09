package com.societe.leavemanagement.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.societe.leavemanagement.dto.CongeDataDTO;
import com.societe.leavemanagement.entities.Collaborateur;
import com.societe.leavemanagement.entities.Conge;
import com.societe.leavemanagement.entities.Utilisateur;
import com.societe.leavemanagement.repository.CollaborateurRepository;
import com.societe.leavemanagement.repository.CongeRepository;
import com.societe.leavemanagement.repository.UserRepository;
import com.societe.leavemanagement.services.CongeService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author salah
 *
 */
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
@Service
@Slf4j
public class CongeServiceImpl implements CongeService {
	/**
	 * 
	 */
	@Autowired
	private UserRepository userRepository;
	/**
	 * 
	 */
	@Autowired
	CollaborateurRepository collaborateurRepository;

	/**
	 * 
	 */
	@Autowired
	CongeRepository congeRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CongeDataDTO> getListCongesCollaborateur(String userName) {
		List<CongeDataDTO> conges = null;
		Collaborateur authentifiedCollaborateur = getCollaborateurByUserName(userName);

		if (authentifiedCollaborateur != null) {
			conges = new ArrayList<>();
			conges.addAll(authentifiedCollaborateur.getConges().stream()
					.map(conge -> new CongeDataDTO(conge.getIdConge(), conge.getCause(), conge.getDateDebut(),
							conge.getDateDemande(), conge.getDateFin(), conge.getEtat(), conge.getNombreJour()))
					.filter(Objects::nonNull).collect(Collectors.toList()));
		}

		return conges;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void storeCongeCollaborateur(Conge conge, String userName) {		
		Collaborateur authentifiedCollaborateur = getCollaborateurByUserName(userName);
		if (authentifiedCollaborateur != null) {
			conge.setCollaborateur(authentifiedCollaborateur);
			congeRepository.save(conge);
		}
	}

	@Override
	public void deleteCongeCollaborateur(int idConge, String userName) {
		if (isLeavePartOfCurrentUserLeaveList(idConge, userName)) {
			congeRepository.deleteById(idConge);
		}
	}

	@Override
	public void updateCongeCollaborateur(int idConge, CongeDataDTO congeDto, String userName) {
		if (isLeavePartOfCurrentUserLeaveList(idConge, userName)) {
			Conge optionalConge = congeRepository.findById(idConge).orElse(null);
			if (optionalConge !=null) {
				optionalConge.setDateDebut(congeDto.getDateDebut());
				optionalConge.setDateFin(congeDto.getDateFin());
				optionalConge.setEtat(congeDto.getEtat());
				optionalConge.setCause(congeDto.getCause());	
				log.info(optionalConge.toString());

			}
		}

	}

	/**
	 * 
	 * @param userName
	 * @return
	 */
	private Collaborateur getCollaborateurByUserName(String userName) {
		Utilisateur utilisateur = userRepository.findByUserName(userName);

		if (utilisateur != null && utilisateur.getCollaborateur() != null) {
			Optional<Collaborateur> collaborateur = collaborateurRepository
					.findById(utilisateur.getCollaborateur().getIdCollaborateur());
			if (collaborateur.isPresent()) {
				return collaborateur.get();
			}

		}
		return null;
	}

	/**
	 * vérifier si le congé fait partie de la liste des congés de l'utilisateur
	 * actuel.
	 * 
	 * @param idConge
	 * @param userName
	 */
	private boolean isLeavePartOfCurrentUserLeaveList(int idConge, String userName) {
		Collaborateur authentifiedCollaborateur = getCollaborateurByUserName(userName);
		if (authentifiedCollaborateur != null) {
			List<Conge> listConges = congeRepository
					.findListCongesByIdCollaborateur(authentifiedCollaborateur.getIdCollaborateur());
			if (listConges != null && !listConges.isEmpty()) {
				List<Conge> filtredListConges = listConges.stream().filter(conge -> conge.getIdConge() == idConge)
						.collect(Collectors.toList());
				return filtredListConges != null && !filtredListConges.isEmpty();
			}
		}
		return false;
	}

}
