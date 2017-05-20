package com.fresher.report.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.fresher.report.entities.Subject;

public class SubjectRepositoryImpl implements ISubjectRepository{

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
