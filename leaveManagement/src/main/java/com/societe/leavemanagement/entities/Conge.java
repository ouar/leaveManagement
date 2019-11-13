package com.societe.leavemanagement.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.societe.leavemanagement.dto.CongeDataDTO;

/**
 * The persistent class for the conge database table.
 * 
 */
@Entity
@NamedQuery(name = "Conge.findAll", query = "SELECT c FROM Conge c")
public class Conge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_CONGE")
	private int idConge;

	@Column(name = "cause")
	private String cause;

	@Column(name = "DATE_Debut")
	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	@Column(name = "DATE_Demande")
	@Temporal(TemporalType.DATE)
	private Date dateDmande;

	@Column(name = "DATE_Fin")
	@Temporal(TemporalType.DATE)
	private Date dateFin;

	@Column(name = "etat")
	private String etat;

	@Column(name = "nombrejour")
	private int nombreJour;

	@Column(name = "type")
	private String type;

	// bi-directional many-to-one association to Collaborateur
	@ManyToOne
	@JoinColumn(name = "ID_COLAB")
	private Collaborateur collaborateur;

	public Conge() {
		super();
	}

	public Conge(CongeDataDTO congeDataDTO) {
		super();	
		this.cause = congeDataDTO.getCause();
		this.dateDebut = congeDataDTO.getDateDebut();
		this.dateDmande = congeDataDTO.getDateDmande();
		this.dateFin = congeDataDTO.getDateFin();
		this.etat = congeDataDTO.getEtat();
		this.nombreJour = congeDataDTO.getNombreJour();
		this.type = congeDataDTO.getType();
	}

	public int getIdConge() {
		return this.idConge;
	}

	public void setIdConge(int idConge) {
		this.idConge = idConge;
	}

	public String getCause() {
		return this.cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	/**
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateDmande
	 */
	public Date getDateDmande() {
		return dateDmande;
	}

	/**
	 * @param dateDmande the dateDmande to set
	 */
	public void setDateDmande(Date dateDmande) {
		this.dateDmande = dateDmande;
	}

	/**
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the etat
	 */
	public String getEtat() {
		return etat;
	}

	/**
	 * @param etat the etat to set
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}

	/**
	 * @return the nombreJour
	 */
	public int getNombreJour() {
		return nombreJour;
	}

	/**
	 * @param nombreJour the nombreJour to set
	 */
	public void setNombreJour(int nombreJour) {
		this.nombreJour = nombreJour;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the collaborateur
	 */
	public Collaborateur getCollaborateur() {
		return collaborateur;
	}

	/**
	 * @param collaborateur the collaborateur to set
	 */
	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}
}