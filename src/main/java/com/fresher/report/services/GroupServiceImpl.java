package com.fresher.report.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresher.report.entities.ClassStatistic;
import com.fresher.report.entities.Group;
import com.fresher.report.entities.PersonStatistic;
import com.fresher.report.repositories.IGroupRepository;
import com.fresher.report.repositories.ISiteClassRepository;
import com.fresher.report.repositories.ITraineeRepository;

@Service("groupService")
public class GroupServiceImpl implements IGroupService {
	@Autowired
	private IGroupRepository groupRepository;
	@Autowired
	private ISiteClassRepository siteClassRepository;
	@Autowired
	private ITraineeRepository traineeRepository;

	public List<Group> findAll() {
		return groupRepository.findAll();
	}

	public void save(Group group) {
		groupRepository.save(group);
	}

	public Group findByName(String name) {
		return groupRepository.findByName(name);
	}

	@Override
	public void updateStatistic() {
		List<Group> groups = groupRepository.findAll();
		for (Group group : groups) {
			String groupName = group.getName();

			ClassStatistic classStatistic = group.getClassStatistic();
			classStatistic.setName("ClassStatistic");
			classStatistic.setTotal(siteClassRepository.countByAttendeeType(groupName));
			classStatistic.setRelease(siteClassRepository.countByAttendeeTypeAndStatus(groupName, "Done"));
			classStatistic.setRunning(siteClassRepository.countByAttendeeTypeAndStatus(groupName, "In progress"));
			group.setClassStatistic(classStatistic);

			PersonStatistic personStatistic = group.getPersonStatistic();
			personStatistic.setName("PersonStatistic");
			personStatistic.setTotal(traineeRepository.countByGroup(groupName));
			personStatistic.setRunning(traineeRepository.countByGroupAndStatus(groupName, "In progress"));
			personStatistic.setRelease(traineeRepository.countByGroupAndStatus(groupName, "Done"));
			group.setPersonStatistic(personStatistic);

			groupRepository.save(group);
		}
	}

}
