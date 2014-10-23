package com.ray.jnm.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ray.jnm.entity.Note;
import com.ray.jnm.entity.Record;

public interface RecordRepository extends JpaRepository<Record, Integer>{

	List<Record> findByNote(Note note, Pageable pageable);
}
