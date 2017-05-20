package com.fresher.report.services;

import java.util.Date;
import java.util.List;

import com.fresher.report.entities.ClassIssue;
import com.fresher.report.entities.SiteClass;

public interface ISiteClassService {

	public void save(SiteClass site);
	
	public List<SiteClass> findAll();

	public SiteClass findOne(String id);
	
	public long countByAttendeeTypeAndStatus(String group, String status);
	
	public long countByAttendeeType(String attendeeType);
	
	public long countByNameAndStatus(String name, String status);
	
	public List<SiteClass> findByStatus(String status);
	
	public long countByGroupAndDate(String group, Date fromDate, Date toDate);
	
	public long countByGroupAndDateAndStatus(String group, Date fromDate, Date toDate, String status);
	
	public List<SiteClass> findByGroupAndDate(String group, Date fromDate, Date toDate);

    public List<SiteClass> findByGroupAndDateAndStatus(String group, Date fromDate, Date toDate, String status);
    
    public Integer countplanCountStudentByGroupAndDateAndStatus(String group, Date fromDate, Date toDate, String status);
    
    public List<ClassIssue> getIssuesByGroup(String groupId);
    
    public List<ClassIssue> getIssuesByGroupAndDate(String groupId, Date fromDate, Date toDate);
}
