package com.societe.leavemanagement.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "utilisateurrole", joinColumns = {
			@JoinColumn(name = "RUS_IDUTILISATEUR_N") }, inverseJoinColumns = { @JoinColumn(name = "RUS_IDROLE_N") })
	private List<Role> roles;

	// bi-directional one-to-one association to Collaborateur
	@OneToOne(mappedBy = "utilisateur")
	private Collaborateur collaborateur;
	
}