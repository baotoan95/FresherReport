package com.fresher.report.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresher.report.entities.SiteClass;
import com.fresher.report.entities.Trainee;
import com.fresher.report.repositories.ITraineeRepository;

@Service("traineeService")
public class TraineeServiceImpl implements ITraineeService {
	@Autowired
	private ITraineeRepository traineeRepository;

	public void save(Trainee trainee, SiteClass siteClass) {
		trainee.setSiteClass(siteClass);
		trainee.setAttendeeType(siteClass.getAttendeeType());
		traineeRepository.save(trainee);
	}

	public List<Trainee> findBySiteClass(String id) {
		return traineeRepository.findBySiteClass(id);
	}

	@Override
	public long countByGroup(String group) {
		return traineeRepository.countByGroup(group);
	}

	@Override
	public long countByGroupAndStatus(String group, String status) {
		return traineeRepository.countByGroupAndStatus(group, status);
	}

	@Override
	public long countBySiteClassAndStatus(String siteClassId, String status) {
		return traineeRepository.countBySiteClassAndStatus(siteClassId, status);
	}

	@Override
	public long countBySiteClass(String siteClassId) {
		return traineeRepository.countBySiteClass(siteClassId);
	}

	@Override
	public long countByGroupAndDate(String group, Date fromDate, Date toDate) {
	    return traineeRepository.countByGroupAndDate(group, fromDate, toDate);
	}
	
	@Override
	public List<Trainee> findAll() {
		return traineeRepository.findAll();
	}

	@Override
	public Trainee findOne(String id) {
		return traineeRepository.findOne(id);
	}

	@Override
	public long countByGroupAndDateAndStatus(String group, Date fromDate, Date toDate, String status) {
		return traineeRepository.countByGroupAndDateAndStatus(group, fromDate, toDate, status);
	}

	@Override
	public List<Trainee> findByGroupAndDate(String group, Date fromDate, Date toDate) {
		return traineeRepository.findByGroupAndDate(group, fromDate, toDate);
	}

	@Override
	public List<Trainee> findByGroupAndDateAndStatus(String group, Date fromDate, Date toDate, String status) {
		return traineeRepository.findByGroupAndDateAndStatus(group, fromDate, toDate, status);
	}

	@Override
	public List<Trainee> findByStatus(String status) {
		return traineeRepository.findByStatus(status);
	}
}