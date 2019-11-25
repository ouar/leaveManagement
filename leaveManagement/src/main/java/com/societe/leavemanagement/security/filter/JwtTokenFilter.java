package com.societe.leavemanagement.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.societe.leavemanagement.security.JwtTokenUtil;
import com.societe.leavemanagement.security.exception.CustomException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Cette classe a les responsabilités suivantes: 1) Recherchez le jeton d'accès
 * dans l'en-tête d'autorisation. Si un jeton d'accès est trouvé dans l'en-tête,
 * déléguez l'authentification à jwtTokenUtil sinon émettre une exception
 * d'authentification. 2) Invoque des stratégies de réussite ou d'échec basées
 * sur le résultat du processus d'authentification exécuté par jwtTokenUtil.
 * 
 * @author salah
 *
 */
@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

	/**
	 * 
	 */
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * {@inheritDoc}.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		String token = jwtTokenUtil.resolveToken(httpServletRequest);
		try {
			if (StringUtils.isNoneBlank(token) && jwtTokenUtil.validateToken(token)) {
				Authentication auth = jwtTokenUtil.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (CustomException ex) {
			log.error(ex.getMessage());
			SecurityContextHolder.clearContext();
			httpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage());
			return;
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}
