package com.societe.leavemanagement.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.societe.leavemanagement.entities.Utilisateur;

/**
 * 
 * @author salah
 *
 */
public interface UserRepository extends JpaRepository<Utilisateur, Integer> {

	/**
	 * 
	 * @param userName
	 * @return
	 */
	@EntityGraph(value = "Utilisateur.collaborateur")
	Utilisateur findByUserName(String userName);

	/**
	 * 
	 * @param userName
	 */
	void deleteByUserName(String userName);

	/**
	 * 
	 * @param userName
	 * @return
	 */
	boolean existsByUserName(String userName);

}