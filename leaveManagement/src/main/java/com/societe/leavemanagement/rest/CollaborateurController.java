package com.societe.leavemanagement.rest;

import java.util.List;

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

import com.societe.leavemanagement.entities.Collaborateur;
import com.societe.leavemanagement.security.exception.CustomException;
import com.societe.leavemanagement.services.LeaveManagementService;

@RestController
@RequestMapping("/collabs")
@PreAuthorize("hasRole('ADMIN')")
public class CollaborateurController {

	@Autowired
	LeaveManagementService leaveManagementService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Collaborateur> getListCollaborateur() {
		try {
			return leaveManagementService.getListCollaborateur();
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Collaborateur> createCollaborateur(@RequestBody Collaborateur collaborateur) {
		try {
			leaveManagementService.store(collaborateur);
			return leaveManagementService.getListCollaborateur();
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping(value = "/{idcollaborateur}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Collaborateur> deleteCollaborateur(@PathVariable int idcollaborateur) {
		try {
			leaveManagementService.deleteCollaborateur(idcollaborateur);
			return leaveManagementService.getListCollaborateur();
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
