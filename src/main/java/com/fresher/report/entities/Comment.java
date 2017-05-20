package com.fresher.report.entities;

import java.util.Date;

public class Comment {
	private Date date;
	private Trainer trainer;
	private String content;

	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(Date date, Trainer trainer, String content) {
		super();
		this.date = date;
		this.trainer = trainer;
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
