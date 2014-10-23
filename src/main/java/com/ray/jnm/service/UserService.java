package com.ray.jnm.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ray.jnm.entity.Note;
import com.ray.jnm.entity.Record;
import com.ray.jnm.entity.Role;
import com.ray.jnm.entity.User;
import com.ray.jnm.entity.WorkGroup;
import com.ray.jnm.repository.NoteRepository;
import com.ray.jnm.repository.RecordRepository;
import com.ray.jnm.repository.RoleRepository;
import com.ray.jnm.repository.UserRepository;
import com.ray.jnm.repository.WorkGroupRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private RecordRepository recordRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	@Transactional
	public User findOneWithNotes(int id) {
		User user = findOne(id);
		List<Note> notes = noteRepository.findByUser(user);
		for (Note n : notes) {
			List<Record> records = recordRepository.findByNote(n,
					new PageRequest(0, 10, Direction.DESC, "publishedDate"));
			//get 10 records in first page
			n.setRecords(records);
		}
		user.setNotes(notes);
		return user;
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);
		
		userRepository.save(user);
	}

	public User findOneWithNotes(String name) {
		User user = userRepository.findByName(name);
		return findOneWithNotes(user.getId());
	}

	public void delete(int id) {
		userRepository.delete(id);
	}

	public void saveChange(User user) {
		userRepository.save(user);
	}

	public User findOne(String userName) {
		User user = userRepository.findByName(userName);
		return user;
	}
	
	
}
