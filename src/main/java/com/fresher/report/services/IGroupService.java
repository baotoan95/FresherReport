package com.fresher.report.services;

import java.util.List;

import com.fresher.report.entities.Group;

public interface IGroupService {
	public List<Group> findAll();

	public void save(Group group);

	public Group findByName(String string);

	public void updateStatistic();

}
