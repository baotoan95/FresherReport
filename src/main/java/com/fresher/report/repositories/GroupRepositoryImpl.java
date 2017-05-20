package com.fresher.report.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.fresher.report.entities.Group;

@Repository("groupRepository")
public class GroupRepositoryImpl implements IGroupRepository {
	@Autowired
	private MongoTemplate mongoTemplate;
	private String COLLECTION = "groups";

	@Override
	public Group findByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return mongoTemplate.findOne(query, Group.class);
	}

	@Override
	public List<Group> findAll() {
		return mongoTemplate.findAll(Group.class, COLLECTION);
	}

	@Override
	public void save(Group group) {
		mongoTemplate.save(group);
	}

	@Override
	public Group findOne(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return mongoTemplate.findOne(query, Group.class);
	}
}
