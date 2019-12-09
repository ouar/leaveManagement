package com.societe.leavemanagement.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The persistent class for the collaborateur database table.
 * 
 */
@Getter
@Setter
@ToString(exclude = {"conges", "soldes", "utilisateur"})
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Collaborateur.findAll", query = "SELECT c FROM Collaborateur c")
@NamedEntityGraphs(value = {
		@NamedEntityGraph(name = "Collaborateur.conges", attributeNodes = @NamedAttributeNode("conges")),
		@NamedEntityGraph(name = "Collaborateur.soldes", attributeNodes = { @NamedAttributeNode("soldes") }) })
public class Collaborateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_COLAB")
	@NonNull
	private Integer idCollaborateur;
	
	@Column(name = "DATENAISSANCE", columnDefinition = "DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NonNull
	private LocalDate dateNaissance;

	@Column(name = "EMAIL")
	@NonNull
	private String email;

	@Column(name = "LIEUNAISSANCE")
	@NonNull
	private String lieuNaissance;

	@Column(name = "NOM")
	@NonNull
	private String nom;

	@Column(name = "PRENOM")
	@NonNull
	private String prenom;

	@Column(name = "TITRE")
	@NonNull
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
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "USR_ID", referencedColumnName = "USR_ID")
	@JsonIgnore
	private Utilisateur utilisateur;

}