package com.fresher.report.repositories;

import java.util.Date;
import java.util.List;

import com.fresher.report.entities.Trainee;

public interface ITraineeRepository {

	public void save(Trainee trainee);

	public List<Trainee> findAll();

	public Trainee findOne(String id);

	public List<Trainee> findBySiteClass(String id);

	public long countByGroup(String group);

	public long countByGroupAndStatus(String group, String status);

	public long countBySiteClassAndStatus(String siteClassId, String status);

	public long countBySiteClass(String siteClassId);

	public long countByGroupAndDate(String group, Date fromDate, Date toDate);
	
	public long countByGroupAndDateAndStatus(String group, Date fromDate, Date toDate, String status);
	
	public List<Trainee> findByGroupAndDate(String group, Date fromDate, Date toDate);

    public List<Trainee> findByGroupAndDateAndStatus(String group, Date fromDate, Date toDate, String status);
    
    public List<Trainee> findByStatus(String status);
}
