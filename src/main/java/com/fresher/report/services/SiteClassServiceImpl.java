package com.fresher.report.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresher.report.entities.ClassIssue;
import com.fresher.report.entities.ClassStatistic;
import com.fresher.report.entities.Group;
import com.fresher.report.entities.PersonStatistic;
import com.fresher.report.entities.SiteClass;
import com.fresher.report.repositories.IGroupRepository;
import com.fresher.report.repositories.ISiteClassRepository;

@Service("siteClassService")
public class SiteClassServiceImpl implements ISiteClassService {
	@Autowired
	private ISiteClassRepository siteClassRepository;
	@Autowired
	private IGroupRepository groupRepository;

	public void save(SiteClass site) {
		// Create new group if it doesn't exist
		if (!"".equalsIgnoreCase(site.getAttendeeType())) {
			Group group = groupRepository.findOne(site.getAttendeeType());
			if (group == null) {
				Group newGroup = new Group();
				newGroup.setId(site.getAttendeeType());
				newGroup.setName(site.getAttendeeType());
				newGroup.setClassStatistic(new ClassStatistic());
				newGroup.setPersonStatistic(new PersonStatistic());
				groupRepository.save(newGroup);
				group = newGroup;
			}
			site.setGroup(group);
		}
		siteClassRepository.save(site);
	}

	public List<SiteClass> findAll() {
		return siteClassRepository.findAll();
	}

	public SiteClass findOne(String id) {
		return siteClassRepository.findOne(id);
	}

	@Override
	public long countByAttendeeTypeAndStatus(String attendeeType, String status) {
		return siteClassRepository.countByAttendeeTypeAndStatus(attendeeType, status);
	}

	@Override
	public long countByAttendeeType(String attendeeType) {
		return siteClassRepository.countByAttendeeType(attendeeType);
	}

	@Override
	public long countByNameAndStatus(String name, String status) {
		return siteClassRepository.countByNameAndStatus(name, status);
	}

	@Override
	public List<SiteClass> findByStatus(String status) {
		return siteClassRepository.findByStatus(status);
	}

	@Override
	public long countByGroupAndDate(String group, Date fromDate, Date toDate) {
		return siteClassRepository.countByGroupAndDate(group, fromDate, toDate);
	}

	@Override
	public long countByGroupAndDateAndStatus(String group, Date fromDate, Date toDate, String status) {
		return siteClassRepository.countByGroupAndDateAndStatus(group, fromDate, toDate, status);
	}

	@Override
	public List<SiteClass> findByGroupAndDate(String group, Date fromDate, Date toDate) {
		return siteClassRepository.findByGroupAndDate(group, fromDate, toDate);
	}

	@Override
	public List<SiteClass> findByGroupAndDateAndStatus(String group, Date fromDate, Date toDate, String status) {
		return siteClassRepository.findByGroupAndDateAndStatus(group, fromDate, toDate, status);
	}

	@Override
	public Integer countplanCountStudentByGroupAndDateAndStatus(String group, Date fromDate, Date toDate,
			String status) {
		List<SiteClass> listClass = siteClassRepository.findByGroupAndDateAndStatus(group, fromDate, toDate, status);
		int count = 0;
		for (SiteClass s : listClass) {
			count += s.getCurrentCountStudent();
		}
		return count;
	}

	@Override
	public List<ClassIssue> getIssuesByGroup(String groupId) {
		List<ClassIssue> listIssue = new ArrayList<>();
		List<SiteClass> siteClasses = siteClassRepository.findByGroup(groupId);
		for (SiteClass siteClass : siteClasses) {
			listIssue.addAll(siteClass.getListIssue());
		}
		return listIssue;
	}

	@Override
	public List<ClassIssue> getIssuesByGroupAndDate(String groupId, Date fromDate, Date toDate) {
		List<ClassIssue> listIssue = new ArrayList<>();
		List<SiteClass> siteClasses = siteClassRepository.findByGroupAndDate(groupId, fromDate, toDate);
		for (SiteClass siteClass : siteClasses) {
			listIssue.addAll(siteClass.getListIssue());
		}
		return listIssue;
	}
}
