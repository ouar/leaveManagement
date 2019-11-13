package com.societe.leavemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.societe.leavemanagement.entities.Conge;

/**
 * 
 * @author salah
 *
 */

public interface CongeRepository extends JpaRepository<Conge, Integer> {

	@EntityGraph(value = "Collaborateur.conges", type = EntityGraphType.LOAD)
	Optional<Conge> findById(Integer id);

}
