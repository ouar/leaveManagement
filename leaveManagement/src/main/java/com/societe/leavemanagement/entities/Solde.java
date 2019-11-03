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

	private int soldeannuel;

	//bi-directional many-to-one association to Collaborateur
	@ManyToOne
	@JoinColumn(name="ID_N")
	private Collaborateur collaborateur;

	public Solde() {
		super();
	}

	public int getIdSolde() {
		return this.idSolde;
	}

	public void setIdSolde(int idSolde) {
		this.idSolde = idSolde;
	}

	public int getSoldeannuel() {
		return this.soldeannuel;
	}

	public void setSoldeannuel(int soldeannuel) {
		this.soldeannuel = soldeannuel;
	}

	public Collaborateur getCollaborateur() {
		return this.collaborateur;
	}

	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}

}