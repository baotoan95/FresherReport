package com.fresher.report.entities;

import java.util.Date;

public class AccessHistory {
	private Date date;
	private String status;
	private String note;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public AccessHistory() {
		// TODO Auto-generated constructor stub
	}

}
