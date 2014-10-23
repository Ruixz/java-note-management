package com.ray.jnm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ray.jnm.entity.User;
import com.ray.jnm.service.UserService;

@Controller
public class AjaxController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="ajaxSearchUser.htm", method=RequestMethod.GET)
	public String ajaxSearchUser(@RequestParam("userName") String userName, Model model) {
		User user = userService.findOne(userName);
		model.addAttribute("ajaxUser", user);
		return "ajaxresult";
	}
}
