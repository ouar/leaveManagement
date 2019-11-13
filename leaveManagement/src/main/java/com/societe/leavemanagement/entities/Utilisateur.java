package com.societe.leavemanagement.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;

/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
@NamedEntityGraph(name = "Utilisateur.collaborateur", attributeNodes = @NamedAttributeNode("collaborateur"))
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USR_ID")
	private int idUtilisateur;
	@Column(name = "USR_NAME")
	private String userName;
	@Column(name = "USR_PASSWORD")
	private String password;

	// bi-directional many-to-many association to Role
	@ManyToMany(fetch = FetchType.EAGER)
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
	 * @return the idUtilisateur
	 */
	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	/**
	 * @param idUtilisateur the idUtilisateur to set
	 */
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
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

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}