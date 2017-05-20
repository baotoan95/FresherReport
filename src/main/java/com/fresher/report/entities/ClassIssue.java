package com.fresher.report.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClassIssue {
	private String id;
	private String content;
	private Date dateCreate;
	private Date dateUpdate;
	private List<Solution> listSolution;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public List<Solution> getListSolution() {
		return listSolution;
	}

	public void setListSolution(List<Solution> listSolution) {
		this.listSolution = listSolution;
	}

	public ClassIssue() {
		this.listSolution = new ArrayList<>();
	}

	public ClassIssue(String content, Date dateCreate, Date dateUpdate, List<Solution> listSolution) {
		this.content = content;
		this.dateCreate = dateCreate;
		this.dateUpdate = dateUpdate;
		this.listSolution = listSolution;
	}

}
