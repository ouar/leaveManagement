package com.societe.leavemanagement.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROL_ID_N")
	private int rolIdN;

	@Column(name="ROL_CODE_C")
	private String rolCodeC;

	@Column(name="ROL_DESCRIPTION_C")
	private String rolDescriptionC;

	//bi-directional many-to-many association to Utilisateur
	@ManyToMany(mappedBy="roles")
	private List<Utilisateur> utilisateurs;

	public Role() {
		super();
	}

	public int getRolIdN() {
		return this.rolIdN;
	}

	public void setRolIdN(int rolIdN) {
		this.rolIdN = rolIdN;
	}

	public String getRolCodeC() {
		return this.rolCodeC;
	}

	public void setRolCodeC(String rolCodeC) {
		this.rolCodeC = rolCodeC;
	}

	public String getRolDescriptionC() {
		return this.rolDescriptionC;
	}

	public void setRolDescriptionC(String rolDescriptionC) {
		this.rolDescriptionC = rolDescriptionC;
	}

	public List<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

}