package com.societe.leavemanagement.dto;

import java.io.Serializable;
import java.util.Date;

public class CongeDataDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String cause;
	/**
	 * 
	 */
	private Date dateDebut;
	/**
	 * 
	 */
	private Date dateDmande;
	/**
	 * 
	 */
	private Date dateFin;
	/**
	 * 
	 */
	private String etat;
	/**
	 * 
	 */
	private int nombreJour;
	/**
	 * 
	 */
	private String type;

	/**
	 * 
	 * @param cause
	 * @param dateDebut
	 * @param dateDmande
	 * @param dateFin
	 * @param etat
	 * @param nombreJour
	 * @param type
	 */
	public CongeDataDTO(String cause, Date dateDebut, Date dateDmande, Date dateFin, String etat, int nombreJour,
			String type) {
		super();
		this.cause = cause;
		this.dateDebut = dateDebut;
		this.dateDmande = dateDmande;
		this.dateFin = dateFin;
		this.etat = etat;
		this.nombreJour = nombreJour;
		this.type = type;
	}

	/**
	 * 
	 */
	public CongeDataDTO() {
		super();

	}

	/**
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * @param cause the cause to set
	 */
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

}
