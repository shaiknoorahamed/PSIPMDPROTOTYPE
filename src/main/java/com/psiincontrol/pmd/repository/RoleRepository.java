package com.psiincontrol.pmd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psiincontrol.pmd.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

}
