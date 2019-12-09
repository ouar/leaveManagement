package com.societe.leavemanagement.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The persistent class for the collaborateur database table.
 * 
 */
@Getter
@Setter
@ToString(exclude = {"conges", "soldes", "utilisateur"})
@Entity
@NamedQuery(name = "Collaborateur.findAll", query = "SELECT c FROM Collaborateur c")
@NamedEntityGraphs(value = {
		@NamedEntityGraph(name = "Collaborateur.conges", attributeNodes = @NamedAttributeNode("conges")),
		@NamedEntityGraph(name = "Collaborateur.soldes", attributeNodes = { @NamedAttributeNode("soldes") }) })
public class Collaborateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_COLAB")
	private int idCollaborateur;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATENAISSANCE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateNaissance;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "LIEUNAISSANCE")
	private String lieuNaissance;

	@Column(name = "NOM")
	private String nom;

	@Column(name = "PRENOM")
	private String prenom;

	@Column(name = "TITRE")
	private String titre;

	// bi-directional many-to-one association to Conge
	@OneToMany(mappedBy = "collaborateur")
	@JsonIgnore
	private List<Conge> conges;

	// bi-directional many-to-one association to Solde
	@OneToMany(mappedBy = "collaborateur")
	@JsonIgnore
	private List<Solde> soldes;

	// bi-directional one-to-one association to Utilisateur
	@OneToOne
	@JoinColumn(name = "USR_ID", referencedColumnName = "USR_ID")
	@JsonIgnore
	private Utilisateur utilisateur;

}