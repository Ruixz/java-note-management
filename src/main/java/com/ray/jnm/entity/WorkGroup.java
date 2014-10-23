package com.ray.jnm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class WorkGroup {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 1, message="Group Name must be at least 1 character!")
	private String name;
	
	@OneToMany(mappedBy="workGroup", cascade=CascadeType.REMOVE)
	private List<User> users;
	
	@OneToMany(mappedBy="workGroup", cascade=CascadeType.REMOVE)
	private List<Project> projects;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
