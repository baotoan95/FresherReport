package com.fresher.report.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.fresher.report.entities.WaitingStatistic;

@Repository("waitingStatisticRepository")
public class WaitingStatisticRepositoryImpl implements IWaitingStatisticRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void save(WaitingStatistic waitingStatistic) {
		mongoTemplate.save(waitingStatistic);
	}

	@Override
	public long countWaitingByGroupNameAndTypeAndDate(String groupName, String type, Date fromDate, Date toDate) {
		Query query = new Query();
		query.addCriteria(Criteria.where("group").is(groupName));
		query.addCriteria(Criteria.where("type").is(type));
		query.addCriteria(Criteria.where("date").gte(fromDate).lte(toDate));
		try {
			query.with(new Sort(Sort.Direction.DESC, "date"));
			List<WaitingStatistic> wts = mongoTemplate.find(query, WaitingStatistic.class);
			return wts.get(0).getValue();
		} catch (Exception e) {
			return 0;
		}
	}
}
