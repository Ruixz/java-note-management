package com.ray.jnm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ray.jnm.entity.Role;
import com.ray.jnm.entity.User;
import com.ray.jnm.entity.WorkGroup;
import com.ray.jnm.service.RoleService;
import com.ray.jnm.service.UserService;
import com.ray.jnm.service.WorkGroupService;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private WorkGroupService workGroupService;

	@Autowired
	private RoleService roleService;

	@ModelAttribute("user")
	public User construct() {
		return new User();
	}

	@ModelAttribute("group")
	public WorkGroup constructWorkGroup() {
		return new WorkGroup();
	}

	@ModelAttribute("role")
	public Role constructRole() {
		return new Role();
	}

	@RequestMapping("/users")
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		model.addAttribute("groups", workGroupService.findAll());
		model.addAttribute("roles", roleService.findAll());
		return "users";
	}

	@RequestMapping("/users/{id}")
	public String detail(Model model, @PathVariable int id) {
		// spring can auto convert {id} string to int
		model.addAttribute("user", userService.findOneWithNotes(id));
		return "user-detail";
	}

	@RequestMapping("/users/remove/{id}")
	public String removeUser(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/users.html";
	}

	// class binding
	@RequestMapping(value = "/users.htm", method = RequestMethod.POST)
	public String doAssignGroup(@RequestParam("user") int id,
			@Validated @ModelAttribute("user") User user) {
		User u = userService.findOne(id);
		u.setWorkGroup(user.getWorkGroup());
		userService.saveChange(u);
		// System.out.println(u.getWorkGroup().getName());
		return "redirect:/users.html";
	}

	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public String doAssignRole(@ModelAttribute("role") Role role,
			@RequestParam("user") int id,
			@Validated @ModelAttribute("user") User user) {
		User u = userService.findOne(id);
		// u.setRoles(user.getRoles());
		System.out.println(role.getName());
		Role saveRole = roleService.findOne(role.getName());
		List<Role> roles = new ArrayList<Role>();
		roles.add(saveRole);
		u.setRoles(roles);
		userService.saveChange(u);
		// System.out.println(u.getWorkGroup().getName());
		return "redirect:/users.html";
	}

	@RequestMapping("/groups")
	public String manageGroup(Model model) {
		model.addAttribute("groups", workGroupService.findAll());
		return "groups";
	}

	@RequestMapping("/groups/remove/{id}")
	public String removeGroup(@PathVariable int id) {
		workGroupService.delete(id);
		return "redirect:/groups.html";
	}

	@RequestMapping(value = "/groups", method = RequestMethod.POST)
	public String addGroup(Model model,
			@Valid @ModelAttribute("group") WorkGroup workGroup,
			BindingResult result) {
		if (result.hasErrors()) {
			return manageGroup(model);
		}
		workGroupService.save(workGroup);
		return "redirect:/groups.html";
	}
}
