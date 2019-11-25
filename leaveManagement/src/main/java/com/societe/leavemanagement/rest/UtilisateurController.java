package com.societe.leavemanagement.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.societe.leavemanagement.dto.UserDataDTO;
import com.societe.leavemanagement.entities.Utilisateur;
import com.societe.leavemanagement.security.exception.CustomException;
import com.societe.leavemanagement.services.AuthenticationService;

@RestController
@RequestMapping("/users")
public class UtilisateurController {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)	
	public UserDataDTO login(@RequestBody UserDataDTO user) {
		try {
			return authenticationService.signin(user.getUserName(), user.getPassword());
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/signup")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String signup(@RequestBody UserDataDTO user) {
		return authenticationService.signup(modelMapper.map(user, Utilisateur.class));
	}

	@DeleteMapping(value = "/{username}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String delete(@PathVariable String username) {
		authenticationService.delete(username);
		return username;
	}

}
