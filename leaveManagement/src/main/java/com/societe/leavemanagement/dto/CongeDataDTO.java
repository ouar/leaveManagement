package com.societe.leavemanagement.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CongeDataDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private int id;
	/**
	 * 
	 */
	private String cause;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDebut;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDemande;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateFin;
	/**
	 * 
	 */
	private String etat;
	/**
	 * 
	 */
	private int nombreJour;

}
