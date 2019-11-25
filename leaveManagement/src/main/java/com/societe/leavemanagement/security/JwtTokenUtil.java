package com.societe.leavemanagement.security;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.societe.leavemanagement.entities.Role;
import com.societe.leavemanagement.security.exception.CustomException;
import com.societe.leavemanagement.security.services.impl.UtilisateurDetailsService;

import lombok.extern.slf4j.Slf4j;

/**
 * Cette classe a les responsabilités suivantes: 1) Vérifier la signature du
 * jeton d'accès. 2) Extraire les revendications d'identité et d'autorisation du
 * jeton d'accès et les utiliser pour créer UserContext. 3) Si le jeton d'accès
 * est mal formé, expiré ou simplement si le jeton n'est pas signé avec la clé
 * de signature appropriée, une exception d'authentification sera levée.
 * 
 * @author salah
 *
 */
@Component
@Slf4j
public class JwtTokenUtil {

	@Value("${security.jwt.token.secret-key:secret-key}")
	private String passwordCertificat;

	@Value("${security.jwt.token.expire-length:3600000}")
	private long validityInMilliseconds = 3600000; // 1h

	@Value("${security.jwt.token.certificat}")
	private String pathCertificat;
	/*
	 * 
	 */
	@Autowired
	private UtilisateurDetailsService userDetailsService;
	/*
	 * 
	 */
	private PrivateKey privateKey;
	/*
	 * 
	 */
	private RSAPublicKey publicKey;

	/**
	 * 
	 */
	@PostConstruct
	protected void init() {
		try {
			privateKey = getPrivateKey();
			publicKey = getPublicKey();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 
	 * @param username
	 * @param roles
	 * @return
	 */
	public String createToken(String username, List<Role> roles) {

		try {
			Date now = new Date();
			Date validity = new Date(now.getTime() + validityInMilliseconds);

			// Prepare JWT with claims set
			JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().subject(username).expirationTime(validity)
					.claim("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getRoleCode()))
							.filter(Objects::nonNull).collect(Collectors.toList()).toString())
					.build();

			RSASSASigner signer = new RSASSASigner(privateKey);
			SignedJWT signedJWTs = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);
			signedJWTs.sign(signer);
			return signedJWTs.serialize();
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 
	 * @param token
	 * @return
	 */
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	/**
	 * 
	 * @param token
	 * @return
	 */
	public String getUsername(String token) {
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);
			RSASSAVerifier verifier = new RSASSAVerifier(publicKey);
			return signedJWT.verify(verifier) ? signedJWT.getJWTClaimsSet().getSubject() : null;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 * @param req
	 * @return
	 */
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	/**
	 * 
	 * @param token
	 * @return
	 */
	public boolean validateToken(String token) {
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);
			RSASSAVerifier verifier = new RSASSAVerifier(publicKey);
			return signedJWT.verify(verifier);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	/**
	 * 
	 * @return
	 */
	private PrivateKey getPrivateKey() {
		try {
			KeyStore keyStore = getKeyStore();
			return (PrivateKey) keyStore.getKey("leavemanagement", passwordCertificat.toCharArray());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 
	 * @return
	 */
	private KeyStore getKeyStore() {

		try (FileInputStream fileInputStream = new FileInputStream(pathCertificat)) {
			KeyStore keyStore = KeyStore.getInstance("pkcs12");
			keyStore.load(fileInputStream, passwordCertificat.toCharArray());
			return keyStore;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 
	 * @return
	 */
	private RSAPublicKey getPublicKey() {
		try {
			KeyStore keyStore = getKeyStore();
			Certificate cert = keyStore.getCertificate("leavemanagement");
			return (RSAPublicKey) cert.getPublicKey();

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
