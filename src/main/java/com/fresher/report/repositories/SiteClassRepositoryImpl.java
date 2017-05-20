package com.fresher.report.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.fresher.report.entities.SiteClass;

@Repository("siteClassRepository")
public class SiteClassRepositoryImpl implements ISiteClassRepository {
	@Autowired
	private MongoTemplate mongoTemplate;
	private final String COLLECTION = "site_classes";

	@Override
	public void save(SiteClass site) {
		mongoTemplate.save(site, COLLECTION);
	}

	@Override
	public List<SiteClass> findAll() {
		return mongoTemplate.findAll(SiteClass.class, COLLECTION);
	}

	@Override
	public SiteClass findOne(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return mongoTemplate.findOne(query, SiteClass.class);
	}

	@Override
	public List<SiteClass> findByStatus(String status) {
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(status));
		return mongoTemplate.find(query, SiteClass.class);
	}

	@Override
	public long countByAttendeeTypeAndStatus(String group, String status) {
		Query query = new Query();
		query.addCriteria(Criteria.where("attendeeType").is(group));
		query.addCriteria(Criteria.where("status").is(status));
		return mongoTemplate.count(query, SiteClass.class);
	}

	@Override
	public long countByAttendeeType(String attendeeType) {
		Query query = new Query();
		query.addCriteria(Criteria.where("attendeeType").is(attendeeType));
		return mongoTemplate.count(query, SiteClass.class);
	}

	@Override
	public long countByNameAndStatus(String name, String status) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		query.addCriteria(Criteria.where("status").is(status));
		return mongoTemplate.count(query, SiteClass.class);
	}

	@Override
	public long countByGroupAndDate(String group, Date fromDate, Date toDate) {
		Query query = new Query();
		query.addCriteria(Criteria.where("group").is(group));
		query.addCriteria(Criteria.where("startDate").gte(fromDate).lte(toDate));
		return mongoTemplate.count(query, SiteClass.class);
	}

	@Override
	public long countByGroupAndDateAndStatus(String group, Date fromDate, Date toDate, String status) {
		Query query = new Query();
		query.addCriteria(Criteria.where("group").is(group));
		query.addCriteria(Criteria.where("startDate").gte(fromDate).lte(toDate));
		query.addCriteria(Criteria.where("status").is(status));
		return mongoTemplate.count(query, SiteClass.class);
	}

	@Override
	public List<SiteClass> findByGroupAndDate(String group, Date fromDate, Date toDate) {
		Query query = new Query();
		query.addCriteria(Criteria.where("group").is(group));
		query.addCriteria(Criteria.where("startDate").gte(fromDate).lte(toDate));
		return mongoTemplate.find(query, SiteClass.class);
	}

	@Override
	public List<SiteClass> findByGroupAndDateAndStatus(String group, Date fromDate, Date toDate, String status) {
		Query query = new Query();
		query.addCriteria(Criteria.where("group").is(group));
		query.addCriteria(Criteria.where("startDate").gte(fromDate).lte(toDate));
		query.addCriteria(Criteria.where("status").is(status));
		return mongoTemplate.find(query, SiteClass.class);
	}

	@Override
	public List<SiteClass> findByGroup(String groupId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("group").is(groupId));
		return mongoTemplate.find(query, SiteClass.class);
	}

}
