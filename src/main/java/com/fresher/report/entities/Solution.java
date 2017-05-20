package com.fresher.report.entities;

import java.util.Date;

public class Solution {
	private String id;
	private String content;
	private Date dateCreate;
	private Date dateUpdate;

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

	public Solution(String content, Date dateCreate, Date dateUpdate) {
		this.content = content;
		this.dateCreate = dateCreate;
		this.dateUpdate = dateUpdate;
	}

	public Solution() {
		
	}
}
