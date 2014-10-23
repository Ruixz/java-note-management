package com.ray.jnm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ray.jnm.entity.Project;
import com.ray.jnm.entity.WorkGroup;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

	List<Project> findByWorkGroup(WorkGroup workGroup);

	Project findByName(String name);

}
