package com.societe.leavemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.societe.leavemanagement.entities.Role;

/**
 * 
 * @author salah
 *
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
