package com.societe.leavemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.societe.leavemanagement.entities.Role;

/**
 * 
 * @author salah
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
