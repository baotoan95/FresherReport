package com.fresher.report.services;

import java.util.List;

import com.fresher.report.entities.Subject;

public interface ISubjectService {

	public void save(Subject subject);

	List<Subject> findAll();

}
