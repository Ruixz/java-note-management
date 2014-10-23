package com.ray.jnm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ray.jnm.entity.Project;
import com.ray.jnm.entity.User;
import com.ray.jnm.entity.WorkGroup;
import com.ray.jnm.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public void save(String saveFileName, User user, WorkGroup workGroup) {
		Project project = new Project();
		project.setName(saveFileName);
		project.setUser(user);
		project.setWorkGroup(workGroup);
		projectRepository.save(project);
	}

	public boolean hasFile(String saveFileName) {
		if(projectRepository.findByName(saveFileName)==null){
			return false;
		}
		return true;
	}

	public Project findOne(int id) {
		return projectRepository.findOne(id);
	}

	//other super user can delete via url
	@PreAuthorize("hasRole('ROLE_SUPER')")
	public void delete(@P("project") Project project) {
		projectRepository.delete(project);
	}

	public Project findOne(String name) {
		return projectRepository.findByName(name);
	}
	
	
}
