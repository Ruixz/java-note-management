package com.ray.jnm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.jnm.entity.Note;
import com.ray.jnm.entity.Record;
import com.ray.jnm.repository.RecordRepository;

@Service
public class RecordService {
	
	@Autowired
	private RecordRepository recordRepository;

	public void save(Record record,Note note) {
		record.setNote(note);
		recordRepository.save(record);
	}
	
}
