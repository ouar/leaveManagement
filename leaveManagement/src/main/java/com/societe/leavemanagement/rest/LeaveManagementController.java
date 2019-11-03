package com.societe.leavemanagement.rest;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.societe.leavemanagement.entities.Collaborateur;
import com.societe.leavemanagement.services.LeaveManagementService;

@RestController
public class LeaveManagementController {

	private static Logger logger = LoggerFactory.getLogger(LeaveManagementController.class);

	@Autowired
	LeaveManagementService leavemanagementService;

	@GetMapping(value = "/store", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object store() {
		Map<String, String> result = new HashMap<String, String>();
		try {
			Collaborateur collaborateur = new Collaborateur();
			collaborateur.setNomC("salah");
			collaborateur.setPrenomC("salah");
			leavemanagementService.store(collaborateur);
			Assert.notNull(collaborateur.getIdN(), "");
			result.put("status", "0");
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("status", "1");
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	


}
