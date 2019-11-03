package com.societe.leavemanagement.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the conge database table.
 * 
 */
@Entity
@NamedQuery(name="Conge.findAll", query="SELECT c FROM Conge c")
public class Conge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CONGE")
	private int idConge;

	private String cause;

	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	@Temporal(TemporalType.DATE)
	private Date dateDmande;

	@Temporal(TemporalType.DATE)
	private Date dateFin;

	private String etat;

	private int nombrejour;

	private String type;

	//bi-directional many-to-one association to Collaborateur
	@ManyToOne
	@JoinColumn(name="ID_N")
	private Collaborateur collaborateur;

	public Conge() {
		super();
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
	 * @return the nombrejour
	 */
	public int getNombrejour() {
		return nombrejour;
	}

	/**
	 * @param nombrejour the nombrejour to set
	 */
	public void setNombrejour(int nombrejour) {
		this.nombrejour = nombrejour;
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