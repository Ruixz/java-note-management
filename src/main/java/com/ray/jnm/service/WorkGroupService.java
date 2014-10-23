package com.ray.jnm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.jnm.entity.Project;
import com.ray.jnm.entity.WorkGroup;
import com.ray.jnm.repository.ProjectRepository;
import com.ray.jnm.repository.UserRepository;
import com.ray.jnm.repository.WorkGroupRepository;

@Service
public class WorkGroupService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WorkGroupRepository workGroupRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public List<WorkGroup> findAll() {
		return workGroupRepository.findAll();
	}

	public void delete(int id) {
		workGroupRepository.delete(id);
	}

	public void save(WorkGroup workGroup) {
		workGroupRepository.save(workGroup);
	}

	public WorkGroup findOne(int parseInt) {
		return workGroupRepository.findOne(parseInt);
	}

	public WorkGroup findOneWithProjects(WorkGroup workGroup) {
		List<Project> projects = projectRepository.findByWorkGroup(workGroup);
		workGroup.setProjects(projects);
		return workGroup;
	}

}
