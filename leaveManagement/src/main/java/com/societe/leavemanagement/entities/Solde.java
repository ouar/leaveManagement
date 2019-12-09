package com.societe.leavemanagement.entities;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the solde database table.
 * 
 */
@Getter
@Setter
@ToString 
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
	
}