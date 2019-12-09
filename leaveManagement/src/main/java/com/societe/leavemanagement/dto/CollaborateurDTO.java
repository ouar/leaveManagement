package com.societe.leavemanagement.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CollaborateurDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateNaissance;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String lieuNaissance;
	/**
	 * 
	 */
	private String nom;
	/**
	 * 
	 */
	private String prenom;
	/**
	 * 
	 */
	private String titre;

}
