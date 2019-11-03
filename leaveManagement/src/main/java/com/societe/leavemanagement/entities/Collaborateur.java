package com.societe.leavemanagement.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the collaborateur database table.
 * 
 */
@Entity
@NamedQuery(name="Collaborateur.findAll", query="SELECT c FROM Collaborateur c")
public class Collaborateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_N")
	private int idN;

	@Temporal(TemporalType.DATE)
	@Column(name="DATENAISSANCE_D")
	private Date datenaissanceD;

	@Column(name="EMAIL_C")
	private String emailC;

	@Column(name="LIEUNAISSANCE_C")
	private String lieunaissanceC;

	@Column(name="NOM_C")
	private String nomC;

	@Column(name="PRENOM_C")
	private String prenomC;

	@Column(name="TITRE_C")
	private String titreC;

	//bi-directional many-to-one association to Conge
	@OneToMany(mappedBy="collaborateur")
	private List<Conge> conges;

	//bi-directional many-to-one association to Solde
	@OneToMany(mappedBy="collaborateur")
	private List<Solde> soldes;

	//bi-directional one-to-one association to Utilisateur
	@OneToOne
	@JoinColumn(name="ID_N", referencedColumnName="USR_ID_N")
	private Utilisateur utilisateur;

	public Collaborateur() {
		super();
	}

	public int getIdN() {
		return this.idN;
	}

	public void setIdN(int idN) {
		this.idN = idN;
	}

	public Date getDatenaissanceD() {
		return this.datenaissanceD;
	}

	public void setDatenaissanceD(Date datenaissanceD) {
		this.datenaissanceD = datenaissanceD;
	}

	public String getEmailC() {
		return this.emailC;
	}

	public void setEmailC(String emailC) {
		this.emailC = emailC;
	}

	public String getLieunaissanceC() {
		return this.lieunaissanceC;
	}

	public void setLieunaissanceC(String lieunaissanceC) {
		this.lieunaissanceC = lieunaissanceC;
	}

	public String getNomC() {
		return this.nomC;
	}

	public void setNomC(String nomC) {
		this.nomC = nomC;
	}

	public String getPrenomC() {
		return this.prenomC;
	}

	public void setPrenomC(String prenomC) {
		this.prenomC = prenomC;
	}

	public String getTitreC() {
		return this.titreC;
	}

	public void setTitreC(String titreC) {
		this.titreC = titreC;
	}

	public List<Conge> getConges() {
		return this.conges;
	}

	public void setConges(List<Conge> conges) {
		this.conges = conges;
	}

	public Conge addConge(Conge conge) {
		getConges().add(conge);
		conge.setCollaborateur(this);

		return conge;
	}

	public Conge removeConge(Conge conge) {
		getConges().remove(conge);
		conge.setCollaborateur(null);

		return conge;
	}

	public List<Solde> getSoldes() {
		return this.soldes;
	}

	public void setSoldes(List<Solde> soldes) {
		this.soldes = soldes;
	}

	public Solde addSolde(Solde solde) {
		getSoldes().add(solde);
		solde.setCollaborateur(this);

		return solde;
	}

	public Solde removeSolde(Solde solde) {
		getSoldes().remove(solde);
		solde.setCollaborateur(null);

		return solde;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}