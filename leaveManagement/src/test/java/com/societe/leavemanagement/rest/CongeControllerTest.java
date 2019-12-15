package com.societe.leavemanagement.rest;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.societe.leavemanagement.entities.Collaborateur;
import com.societe.leavemanagement.entities.Conge;
import com.societe.leavemanagement.entities.Role;
import com.societe.leavemanagement.entities.Utilisateur;
import com.societe.leavemanagement.security.JwtTokenUtil;
import com.societe.leavemanagement.services.CollaborateurService;
import com.societe.leavemanagement.services.CongeService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.config.name=leaveManagement_test" }, webEnvironment = WebEnvironment.RANDOM_PORT)
class CongeControllerTest {
	
	@LocalServerPort
	int randomServerPort;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	private CongeService congeService;
	
	@Autowired
	private CollaborateurService collaborateurService;

	/**
	 * 
	 */
	@BeforeEach
	public void poulateData() {
		Collaborateur collaborateur = new Collaborateur(0, LocalDate.now(), "salah.ouar@yahoo.fr", "ALGERIE", "brad","pitt", "president");
		Utilisateur user = new Utilisateur(1, "salah", "password", null, collaborateur);
		Role role = new Role();
		role.setRoleCode("ADMIN");
		role.setIdRole(1);
		role.setRoleDescription("ADMIN");
		user.setRoles(Arrays.asList(role));
		collaborateur.setUtilisateur(user);
		collaborateurService.storeCollaborateur(collaborateur);
		congeService.storeCongeCollaborateur(new Conge(2, "cause", LocalDate.now(), LocalDate.now(), LocalDate.now(), "etat", 0), "salah");
	}

	@Test
	void testGetListCongesCollaborateur() throws URISyntaxException {
		Role role = new Role();
		role.setRoleCode("ROLE_ADMIN");
		role.setIdRole(1);
		role.setRoleDescription("ROLE_ADMIN");
		String accessToken = jwtTokenUtil.createToken("salah", Arrays.asList(role));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+ accessToken);
		
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/leaves";
		URI uri = new URI(baseUrl);
		HttpEntity<String> entity = new HttpEntity<>("body", headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);	

		// Verify request succeed
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(true, result.getBody().contains("dateDebut"));
	}

	@Ignore
	@Test
	void testAddConge() {
		
	}

	@Ignore
	@Test
	void testDeleteConge() {
		
	}

	@Ignore
	@Test
	void testUpdateConge() {
		
	}

}
