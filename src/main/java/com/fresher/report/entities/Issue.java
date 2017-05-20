package com.fresher.report.entities;

import java.util.Date;

public class Issue {
	private String name;
	private Date date;
	private float excluded; // Diem tru
	private String note;

	public Issue() {
		// TODO Auto-generated constructor stub
	}

	public Issue(String name, Date date, float excluded, String note) {
		super();
		this.name = name;
		this.date = date;
		this.excluded = excluded;
		this.note = note;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getExcluded() {
		return excluded;
	}

	public void setExcluded(float excluded) {
		this.excluded = excluded;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
