package com.societe.leavemanagement.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
@NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USR_ID_N")
	private int idN;

	// bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(name = "utilisateurrole", joinColumns = {
			@JoinColumn(name = "RUS_IDUTILISATEUR_N") }, inverseJoinColumns = { @JoinColumn(name = "RUS_IDROLE_N") })
	private List<Role> roles;

	// bi-directional one-to-one association to Collaborateur
	@OneToOne(mappedBy = "utilisateur")
	private Collaborateur collaborateur;

	public Utilisateur() {
		super();
	}

	/**
	 * @return the idN
	 */
	public int getIdN() {
		return idN;
	}

	/**
	 * @param idN the idN to set
	 */
	public void setIdN(int idN) {
		this.idN = idN;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
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