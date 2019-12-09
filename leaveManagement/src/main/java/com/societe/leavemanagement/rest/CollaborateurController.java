package com.societe.leavemanagement.rest;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.societe.leavemanagement.dto.CollaborateurDataDTO;
import com.societe.leavemanagement.entities.Collaborateur;
import com.societe.leavemanagement.security.exception.CustomException;
import com.societe.leavemanagement.services.CollaborateurService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/collabs")
@PreAuthorize("hasAuthority('ADMIN')")
@Slf4j
public class CollaborateurController {

	@Autowired
	CollaborateurService collaborateurService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Collaborateur> getListCollaborateur() {
		try {
			return collaborateurService.getListCollaborateurs();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Collaborateur> createCollaborateur(@RequestBody CollaborateurDataDTO collaborateurDataDTO) {
		try {
			collaborateurService.storeCollaborateur(modelMapper.map(collaborateurDataDTO, Collaborateur.class));
			return collaborateurService.getListCollaborateurs();
		}  catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping(value = "/{idcollaborateur}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Collaborateur> deleteCollaborateur(@PathVariable int idcollaborateur) {
		try {
			collaborateurService.deleteCollaborateur(idcollaborateur);
			return collaborateurService.getListCollaborateurs();
		}  catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
