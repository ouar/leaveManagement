package com.societe.leavemanagement.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.societe.leavemanagement.dto.CongeDataDTO;
import com.societe.leavemanagement.entities.Collaborateur;
import com.societe.leavemanagement.entities.Conge;
import com.societe.leavemanagement.entities.Utilisateur;
import com.societe.leavemanagement.repository.CollaborateurRepository;
import com.societe.leavemanagement.repository.CongeRepository;
import com.societe.leavemanagement.repository.UserRepository;
import com.societe.leavemanagement.services.LeaveManagementService;

/**
 * 
 * @author salah
 *
 */
@Service
@Transactional(value = TxType.REQUIRES_NEW)
public class LeaveManagementServiceImpl implements LeaveManagementService {
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
	public void store(Collaborateur collaborateur) {
		collaborateurRepository.save(collaborateur);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CongeDataDTO> getListCongesCollaborateur(String userName) {
		List<CongeDataDTO> conges = null;
		Utilisateur utilisateur = userRepository.findByUserName(userName);
		
		if (utilisateur != null && utilisateur.getCollaborateur() != null) {
			Optional<Collaborateur> collaborateur = collaborateurRepository.findById(utilisateur.getCollaborateur().getIdCollaborateur());

			if (collaborateur.isPresent()) {
				conges = new ArrayList<>();
				conges.addAll(collaborateur.get().getConges().stream()
						.map(conge -> new CongeDataDTO(conge.getCause(), conge.getDateDebut(), conge.getDateDmande(),
								conge.getDateFin(), conge.getEtat(), conge.getNombreJour(), conge.getType()))
						.filter(Objects::nonNull).collect(Collectors.toList()));
			}

		}
		return conges;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Collaborateur> getListCollaborateur() {
		return collaborateurRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void store(Conge conge) {
		congeRepository.save(conge);

	}

	@Override
	public void delete(Conge conge) {
		congeRepository.delete(conge);

	}

	@Override
	public void deleteCollaborateur(int idcollaborateur) {
		collaborateurRepository.deleteById(idcollaborateur);
	}
}
