package com.ray.jnm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ray.jnm.entity.Note;
import com.ray.jnm.entity.Record;
import com.ray.jnm.entity.User;
import com.ray.jnm.repository.NoteRepository;
import com.ray.jnm.repository.RecordRepository;
import com.ray.jnm.repository.UserRepository;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RecordRepository recordRepository;

	public void save(Note note, String name) {
		User user = userRepository.findByName(name);
		note.setUser(user);
		noteRepository.save(note);
	}

	// decides whether a method can actually be invoked or not
	// in this case, only authentication user or role_admin can call delete
	@PreAuthorize("#note.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("note") Note note) {
		noteRepository.delete(note);
	}

	public Note findOne(int id) {
		return noteRepository.findOne(id);
	}

	public void saveChange(Note n) {
		noteRepository.save(n);
	}

	@Transactional
	public Note findOneWithRecord(int id) {
		Note note = findOne(id);
		List<Record> records = recordRepository.findByNote(note,
				new PageRequest(0, 10, Direction.DESC, "publishedDate"));
		note.setRecords(records);
		return note;
	}
}
