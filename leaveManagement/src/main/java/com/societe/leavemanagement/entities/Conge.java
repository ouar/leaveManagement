package com.societe.leavemanagement.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The persistent class for the conge database table.
 * 
 */
@Getter
@Setter
@ToString(exclude = {"nombreJour"})
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Conge.findAll", query = "SELECT c FROM Conge c")
@NamedQuery(name = "Conge.findListCongesByIdCollaborateur", query = "SELECT new com.societe.leavemanagement.entities.Conge(conge.idConge, conge.cause, conge.dateDebut, conge.dateDemande, conge.dateFin, conge.etat, conge.nombreJour) FROM Conge conge JOIN conge.collaborateur collaborateur WHERE collaborateur.idCollaborateur=?1")
public class Conge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_CONGE")
	@NonNull
	private Integer idConge;
	
	@Column(name = "cause")
	@NonNull
	private String cause;

	
	@Column(name = "DATE_Debut", columnDefinition = "DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NonNull
	private LocalDate dateDebut;
	
	@Column(name = "DATE_Demande", columnDefinition = "DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NonNull
	private LocalDate dateDemande;

	@Column(name = "DATE_Fin", columnDefinition = "DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NonNull
	private LocalDate dateFin;

	@Column(name = "etat")
	@NonNull
	private String etat;

	@Column(name = "nombrejour")
	@NonNull
	private Integer nombreJour;

	// bi-directional many-to-one association to Collaborateur
	@ManyToOne
	@JoinColumn(name = "ID_COLAB")
	private Collaborateur collaborateur;	
	
}