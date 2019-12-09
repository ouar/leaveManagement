package com.societe.leavemanagement.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * The persistent class for the role database table.
 * 
 */
@Getter
@Setter
@ToString
@Entity
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ROL_ID")
	private int idRole;

	@Column(name = "ROL_CODE")
	private String roleCode;

	@Column(name = "ROL_DESCRIPTION")
	private String roleDescription;

	// bi-directional many-to-many association to Utilisateur
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private List<Utilisateur> utilisateurs;

}