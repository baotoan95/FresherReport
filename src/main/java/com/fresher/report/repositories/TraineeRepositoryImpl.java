package com.fresher.report.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.fresher.report.entities.Trainee;

@Repository("traineeRepository")
public class TraineeRepositoryImpl implements ITraineeRepository {
	@Autowired
	private MongoTemplate mongoTemplate;
	private static final String COLLECTION = "trainees";

	@Override
	public void save(Trainee trainee) {
		mongoTemplate.save(trainee, COLLECTION);
	}

	@Override
	public List<Trainee> findAll() {
		return mongoTemplate.findAll(Trainee.class, COLLECTION);
	}

	@Override
	public Trainee findOne(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return mongoTemplate.findOne(query, Trainee.class);
	}
	
	@Override
	public List<Trainee> findBySiteClass(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("siteClass").is(id));
		return mongoTemplate.find(query, Trainee.class);
	}

	@Override
	public long countByGroup(String group) {
		Query query = new Query();
		query.addCriteria(Criteria.where("group").is(group));
		return mongoTemplate.count(query, Trainee.class);
	}

	@Override
	public long countByGroupAndStatus(String group, String status) {
		Query query = new Query();
		query.addCriteria(Criteria.where("group").is(group));
		query.addCriteria(Criteria.where("status").is(status));
		return mongoTemplate.count(query, Trainee.class);
	}

	@Override
	public long countBySiteClassAndStatus(String siteClassId, String status) {
		Query query = new Query();
		query.addCriteria(Criteria.where("siteClass").is(siteClassId));
		query.addCriteria(Criteria.where("status").is(status));
		return mongoTemplate.count(query, Trainee.class);
	}

	@Override
	public long countBySiteClass(String siteClassId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("siteClass").is(siteClassId));
		return mongoTemplate.count(query, Trainee.class);
	}

	@Override
	public long countByGroupAndDate(String group, Date fromDate, Date toDate) {
		Query query = new Query();
		query.addCriteria(Criteria.where("attendeeType").is(group));
		query.addCriteria(Criteria.where("startDate").gte(fromDate).lte(toDate));
		return mongoTemplate.count(query, Trainee.class);
	}

	@Override
	public long countByGroupAndDateAndStatus(String group, Date fromDate, Date toDate, String status) {
		Query query = new Query();
		query.addCriteria(Criteria.where("attendeeType").is(group));
		query.addCriteria(Criteria.where("startDate").gte(fromDate).lte(toDate));
		query.addCriteria(Criteria.where("status").is(status));
		return mongoTemplate.count(query, Trainee.class);
	}

	@Override
	public List<Trainee> findByGroupAndDate(String group, Date fromDate, Date toDate) {
		Query query = new Query();
		query.addCriteria(Criteria.where("attendeeType").is(group));
		query.addCriteria(Criteria.where("startDate").gte(fromDate).lte(toDate));
		return mongoTemplate.find(query, Trainee.class);
	}

	@Override
	public List<Trainee> findByGroupAndDateAndStatus(String group, Date fromDate, Date toDate, String status) {
		Query query = new Query();
		query.addCriteria(Criteria.where("attendeeType").is(group));
		query.addCriteria(Criteria.where("startDate").gte(fromDate).lte(toDate));
		query.addCriteria(Criteria.where("status").is(status));
		return mongoTemplate.find(query, Trainee.class);
	}

	@Override
	public List<Trainee> findByStatus(String status) {
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(status));
		return mongoTemplate.find(query, Trainee.class);
	}

}
