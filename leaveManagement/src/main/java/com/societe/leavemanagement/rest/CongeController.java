package com.societe.leavemanagement.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.societe.leavemanagement.dto.CongeDataDTO;
import com.societe.leavemanagement.entities.Conge;
import com.societe.leavemanagement.security.exception.CustomException;
import com.societe.leavemanagement.services.LeaveManagementService;

@RestController
@RequestMapping("/leaves")
@PreAuthorize("hasAuthority('ADMIN')")
public class CongeController {

	@Autowired
	LeaveManagementService leaveManagementService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CongeDataDTO>> getListCongesCollaborateur(@AuthenticationPrincipal UserDetails userDetails) {
		try {
			String userName = userDetails.getUsername();
			List<CongeDataDTO> conges = leaveManagementService.getListCongesCollaborateur(userName);
			return ResponseEntity.ok(conges);
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CongeDataDTO>> addConge(@RequestBody CongeDataDTO congeDataDTO,
			@AuthenticationPrincipal UserDetails userDetails) {
		try {
			String userName = userDetails.getUsername();
			Conge conge = new Conge(congeDataDTO);
			leaveManagementService.store(conge);
			return ResponseEntity.ok(leaveManagementService.getListCongesCollaborateur(userName));
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
