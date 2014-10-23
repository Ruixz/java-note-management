package com.ray.jnm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private RecordRepository recordRepository;
	
	@Autowired
	private WorkGroupRepository workGroupRepository;
	
	@PostConstruct
	public void init(){
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		Role roleSuper = new Role();
		roleSuper.setName("ROLE_SUPER");
		roleRepository.save(roleSuper);
		
		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		userAdmin.setEmail("admin@admin.com");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
//		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		User userTest = new User();
		userTest.setEnabled(true);
		userTest.setName("test");
		userTest.setEmail("test@test.com");
		userTest.setPassword(encoder.encode("test"));
		List<Role> testRoles = new ArrayList<Role>();
		testRoles.add(roleUser);
		userTest.setRoles(testRoles);
		userRepository.save(userTest);
		
		User userSuper = new User();
		userSuper.setEnabled(true);
		userSuper.setName("super");
		userSuper.setEmail("super@super.com");
		userSuper.setPassword(encoder.encode("super"));
		List<Role> superRoles = new ArrayList<Role>();
		superRoles.add(roleSuper);
		userSuper.setRoles(superRoles);
		userRepository.save(userSuper);
		
		WorkGroup workGroup = new WorkGroup();
		workGroup.setName("Java");
		List<User> users = new ArrayList<User>();
		users.add(userTest);
		workGroup.setUsers(users);
		userTest.setWorkGroup(workGroup);
		workGroupRepository.save(workGroup);
		userRepository.save(userTest);
		
		Note noteRay = new Note();
		noteRay.setName("Web Tool");
		noteRay.setLocation("Boston");
		noteRay.setUser(userAdmin);
		noteRepository.save(noteRay);
		
		Record record1 = new Record();
		record1.setNote(noteRay);
		record1.setTitle("Spring");
		record1.setDescription("Spring is a Java web framework.");
		record1.setPublishedDate(new Date());
		recordRepository.save(record1);
		
		Record record2 = new Record();
		record2.setNote(noteRay);
		record2.setTitle("Hibernate");
		record2.setDescription("Hibernate maintance data persistence.");
		record2.setPublishedDate(new Date());
		recordRepository.save(record2);
	}
	
}
