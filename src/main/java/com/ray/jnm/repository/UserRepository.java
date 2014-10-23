package com.ray.jnm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ray.jnm.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByName(String name);

}
