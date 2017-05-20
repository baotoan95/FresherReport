package com.fresher.report.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.fresher.report.entities.Subject;

@Service("subjectService")
public class SubjectServiceImpl implements ISubjectService {

	@Autowired
	private MongoTemplate mongoTemplate;
	private static final String COLLECTION = "subject";

	@Override
	public void save(Subject subject) {
		mongoTemplate.save(subject, COLLECTION);

	}

	@Override
	public List<Subject> findAll() {
		return mongoTemplate.findAll(Subject.class, COLLECTION);
	}

}
