package com.fresher.report.entities;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subjects")
public class Subject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private Trainer trainer;
	private List<Feedback> listFeedback;
	private String note;

	public Subject() {
		// TODO Auto-generated constructor stub
	}

	public Subject(String id, String name, Trainer trainer, List<Feedback> listFeedback, String note) {
		super();
		this.id = id;
		this.name = name;
		this.trainer = trainer;
		this.listFeedback = listFeedback;
		this.note = note;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public List<Feedback> getListFeedback() {
		return listFeedback;
	}

	public void setListFeedback(List<Feedback> listFeedback) {
		this.listFeedback = listFeedback;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
