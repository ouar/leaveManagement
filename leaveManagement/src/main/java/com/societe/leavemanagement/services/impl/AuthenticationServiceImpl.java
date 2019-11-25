package com.societe.leavemanagement.services.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.societe.leavemanagement.dto.UserDataDTO;
import com.societe.leavemanagement.entities.Role;
import com.societe.leavemanagement.entities.Utilisateur;
import com.societe.leavemanagement.repository.UserRepository;
import com.societe.leavemanagement.security.JwtTokenUtil;
import com.societe.leavemanagement.security.exception.CustomException;
import com.societe.leavemanagement.services.AuthenticationService;

/**
 * 
 * @author salah
 *
 */
@Service
@Transactional(value = TxType.REQUIRES_NEW)
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	/**
	 * 
	 */
	@Autowired
	private JmsTemplate jmsQueueTemplate;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDataDTO signin(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			List<Role> listRoles = userRepository.findByUserName(username).getRoles();
			String token = jwtTokenUtil.createToken(username, userRepository.findByUserName(username).getRoles());
			jmsQueueTemplate.convertAndSend("activemq/queue/TestInQueue", "Authentification de l'utilisateur" + username);
			UserDataDTO userResponse = new UserDataDTO();
			userResponse.setUserName(username);
			userResponse.setToken(token);
			userResponse.setRoles(listRoles);
			return userResponse;
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String signup(Utilisateur user) {
		if (!userRepository.existsByUserName(user.getUserName())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return jwtTokenUtil.createToken(user.getUserName(), user.getRoles());
		} else {
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String username) {
		userRepository.deleteByUserName(username);
	}	

}
