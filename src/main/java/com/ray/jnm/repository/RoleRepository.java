package com.ray.jnm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ray.jnm.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String name);

}
