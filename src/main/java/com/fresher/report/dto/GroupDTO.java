package com.fresher.report.dto;

import java.util.List;

import com.fresher.report.entities.ClassIssue;
import com.fresher.report.entities.Group;

public class GroupDTO {
	private Group group;
	private List<ClassIssue> listClassIssues;

	public GroupDTO() {
		// TODO Auto-generated constructor stub
	}

	public GroupDTO(Group group, List<ClassIssue> listClassIssues) {
		super();
		this.group = group;
		this.listClassIssues = listClassIssues;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<ClassIssue> getListClassIssues() {
		return listClassIssues;
	}

	public void setListClassIssues(List<ClassIssue> listClassIssues) {
		this.listClassIssues = listClassIssues;
	}

}
