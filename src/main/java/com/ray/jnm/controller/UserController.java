package com.ray.jnm.controller;

import java.security.Principal;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ray.jnm.entity.Note;
import com.ray.jnm.entity.Record;
import com.ray.jnm.service.NoteService;
import com.ray.jnm.service.RecordService;
import com.ray.jnm.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private NoteService noteService;

	@Autowired
	private RecordService recordService;

	@ModelAttribute
	public Note constructNote() {
		return new Note();
	}

	@ModelAttribute("record")
	public Record constructRecord() {
		return new Record();
	}

	@RequestMapping("/account")
	public String account(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithNotes(name));
		return "account";
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String doAddNote(Model model, Principal principal,
			@Valid @ModelAttribute("note") Note note, BindingResult result) {
		if (result.hasErrors()) {
			return account(model, principal);
		}
		String name = principal.getName();
		noteService.save(note, name);
		return "redirect:/account.html";
	}

	@RequestMapping("/note/remove/{id}")
	public String removeNote(@PathVariable int id) {
		Note note = noteService.findOne(id);
		noteService.delete(note);
		return "redirect:/account.html";
	}

	//WTF!!!!!@PathVariable id will become modelAttribute entity id!!!Change name of id to solve it
	// attention:lazy initialization exception
	@RequestMapping(value = "/note/{noteid}", method = RequestMethod.GET)
	public String doAddRecord(@ModelAttribute("record") Record record,
			@PathVariable("noteid") int id) {
		Note n = noteService.findOne(id);
		record.setPublishedDate(new Date());
		System.out.println("record id:"+record.getId());
		recordService.save(record, n);
		return "redirect:/account.html";
	}

}
