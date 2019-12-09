package com.societe.leavemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.societe.leavemanagement.entities.Collaborateur;

/**
 * 
 * @author salah
 *
 */

@Repository
public interface CollaborateurRepository extends JpaRepository<Collaborateur, Integer> {

}
