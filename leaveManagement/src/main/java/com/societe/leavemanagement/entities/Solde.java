package com.societe.leavemanagement.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the solde database table.
 * 
 */
@Entity
@NamedQuery(name="Solde.findAll", query="SELECT s FROM Solde s")
public class Solde implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_SOLDE")
	private int idSolde;

	private int soldeAnnuel;

	//bi-directional many-to-one association to Collaborateur
	@ManyToOne
	@JoinColumn(name="ID_N")
	private Collaborateur collaborateur;

	public Solde() {
		super();
	}

	/**
	 * @return the idSolde
	 */
	public int getIdSolde() {
		return idSolde;
	}

	/**
	 * @param idSolde the idSolde to set
	 */
	public void setIdSolde(int idSolde) {
		this.idSolde = idSolde;
	}

	/**
	 * @return the soldeAnnuel
	 */
	public int getSoldeAnnuel() {
		return soldeAnnuel;
	}

	/**
	 * @param soldeAnnuel the soldeAnnuel to set
	 */
	public void setSoldeAnnuel(int soldeAnnuel) {
		this.soldeAnnuel = soldeAnnuel;
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