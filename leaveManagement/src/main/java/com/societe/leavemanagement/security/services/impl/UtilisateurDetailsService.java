package com.societe.leavemanagement.security.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.societe.leavemanagement.entities.Utilisateur;
import com.societe.leavemanagement.repository.UserRepository;

/**
 * Cette classe sert à définir notre propre fonction loadUserbyUsername
 * personnalisée. L'interface {@link UserDetailsService} est utilisée pour
 * récupérer des données relatives à l'utilisateur. Il possède une méthode
 * nommée loadUserByUsername () qui trouve une entité utilisateur en fonction du
 * nom d'utilisateur et peut être remplacée pour personnaliser le processus de
 * recherche de l'utilisateur. Il est utilisé par
 * {@link DaoAuthenticationProvider} pour charger les détails de l'utilisateur
 * pendant l'authentification.
 * 
 * @author salah
 *
 */
@Service
@Transactional(value = TxType.REQUIRES_NEW)
public class UtilisateurDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		final Utilisateur user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}
		List<SimpleGrantedAuthority> listAuthority = new ArrayList<>();

		listAuthority.addAll(user.getRoles().stream().map(s -> new SimpleGrantedAuthority(s.getRoleCode()))
				.filter(Objects::nonNull).collect(Collectors.toList()));

		return User.withUsername(username).password(user.getPassword()).authorities(listAuthority).accountExpired(false)
				.accountLocked(false).credentialsExpired(false).disabled(false).build();
	}

}