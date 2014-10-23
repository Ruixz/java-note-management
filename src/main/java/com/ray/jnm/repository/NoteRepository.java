package com.ray.jnm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ray.jnm.entity.Note;
import com.ray.jnm.entity.User;

public interface NoteRepository extends JpaRepository<Note, Integer>{

	List<Note> findByUser(User user);
}
