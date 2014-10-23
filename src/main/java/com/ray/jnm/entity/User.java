package com.ray.jnm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.ray.jnm.annotation.UniqueUsername;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 3, message = "Name must be at least 3 characters!")
	@Column(unique = true)
	@UniqueUsername(message = "This username already exists!")
	private String name;
	
	@Size(min = 1, message = "Invalid email address!")
	@Email(message = "Invalid email address!")
	private String email;
	
	@Size(min = 3, message = "Name must be at least 3 characters!")
	private String password;
	
	private boolean enabled;
	
	@ManyToMany
	@JoinTable
	private List<Role> roles;

	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	private List<Note> notes;
	
	@ManyToOne
	@JoinColumn(name="workGroup_id")
	private WorkGroup workGroup;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	private List<Project> projects;
	
//	@OneToMany(mappedBy="receiver")
//	private List<Request> receivedRequests;
//	
//	@OneToMany(mappedBy="sender")
//	private List<Request> sendRequests;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public WorkGroup getWorkGroup() {
		return workGroup;
	}

	public void setWorkGroup(WorkGroup workGroup) {
		this.workGroup = workGroup;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

//	public List<Request> getReceivedRequests() {
//		return receivedRequests;
//	}
//
//	public void setReceivedRequests(List<Request> receivedRequests) {
//		this.receivedRequests = receivedRequests;
//	}
//
//	public List<Request> getSendRequests() {
//		return sendRequests;
//	}
//
//	public void setSendRequests(List<Request> sendRequests) {
//		this.sendRequests = sendRequests;
//	}
	
	
}
