package com.fresher.report.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fresher.report.entities.Subject;

@Repository("subjectRepository")
public interface ISubjectRepository{
	
	public void save(Subject subject);

	public List<Subject> findAll();

}

