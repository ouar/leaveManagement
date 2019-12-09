package com.societe.leavemanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.societe.leavemanagement.entities.Conge;

/**
 * 
 * @author salah
 *
 */
@Repository
public interface CongeRepository extends JpaRepository<Conge, Integer> {

	/**
	 * 
	 */
	@EntityGraph(value = "Collaborateur.conges", type = EntityGraphType.LOAD)
	Optional<Conge> findById(Integer id);

	/**
	 * 
	 * @param IdCollaborateur
	 * @return
	 */
	List<Conge> findListCongesByIdCollaborateur(Integer idCollaborateur);

}
