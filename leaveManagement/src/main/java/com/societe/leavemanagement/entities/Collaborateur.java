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

/**
 * The persistent class for the collaborateur database table.
 * 
 */
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
	private List<Conge> conges;

	// bi-directional many-to-one association to Solde
	@OneToMany(mappedBy = "collaborateur")
	private List<Solde> soldes;

	// bi-directional one-to-one association to Utilisateur
	@OneToOne
	@JoinColumn(name = "USR_ID", referencedColumnName = "USR_ID")
	private Utilisateur utilisateur;

	public Collaborateur() {
		super();
	}

	/**
	 * @return the idCollaborateur
	 */
	public int getIdCollaborateur() {
		return idCollaborateur;
	}

	/**
	 * @param idCollaborateur the idCollaborateur to set
	 */
	public void setIdCollaborateur(int idCollaborateur) {
		this.idCollaborateur = idCollaborateur;
	}

	/**
	 * @return the dateNaissance
	 */
	public Date getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the lieuNaissance
	 */
	public String getLieuNaissance() {
		return lieuNaissance;
	}

	/**
	 * @param lieuNaissance the lieuNaissance to set
	 */
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the conges
	 */
	public List<Conge> getConges() {
		return conges;
	}

	/**
	 * @param conges the conges to set
	 */
	public void setConges(List<Conge> conges) {
		this.conges = conges;
	}

	/**
	 * @return the soldes
	 */
	public List<Solde> getSoldes() {
		return soldes;
	}

	/**
	 * @param soldes the soldes to set
	 */
	public void setSoldes(List<Solde> soldes) {
		this.soldes = soldes;
	}

	/**
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}