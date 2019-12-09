package com.societe.leavemanagement.rest;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.societe.leavemanagement.dto.CongeDataDTO;
import com.societe.leavemanagement.entities.Conge;
import com.societe.leavemanagement.security.exception.CustomException;
import com.societe.leavemanagement.services.CongeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/leaves")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
@Slf4j
public class CongeController {


	@Autowired
	CongeService congeService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CongeDataDTO>> getListCongesCollaborateur(
			@AuthenticationPrincipal UserDetails userDetails) {
		try {
			List<CongeDataDTO> conges = congeService.getListCongesCollaborateur(userDetails.getUsername());
			return ResponseEntity.ok(conges);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CongeDataDTO>> addConge(@RequestBody CongeDataDTO congeDataDTO,
			@AuthenticationPrincipal UserDetails userDetails) {
		try {		
			congeDataDTO.setEtat("C");	
			congeDataDTO.setDateDemande(LocalDate.now());			 
			Period period = Period.between(congeDataDTO.getDateDebut(), congeDataDTO.getDateFin());
			congeDataDTO.setNombreJour(period.getDays());
			congeService.storeCongeCollaborateur(modelMapper.map(congeDataDTO, Conge.class), userDetails.getUsername());
			return ResponseEntity.ok(congeService.getListCongesCollaborateur(userDetails.getUsername()));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CongeDataDTO>> deleteConge(@PathVariable int id, @AuthenticationPrincipal UserDetails userDetails) {
		try {
			congeService.deleteCongeCollaborateur(id, userDetails.getUsername());
			return ResponseEntity.ok(congeService.getListCongesCollaborateur(userDetails.getUsername()));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CongeDataDTO>> updateConge(@PathVariable int id,@RequestBody CongeDataDTO congeDataDTO, @AuthenticationPrincipal UserDetails userDetails) {
		try {
			congeService.updateCongeCollaborateur(id, congeDataDTO, userDetails.getUsername());
			return ResponseEntity.ok(congeService.getListCongesCollaborateur(userDetails.getUsername()));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
