package com.fresher.report.entities;

public class Skill {
	private String name;
	private double level;
	private String note;

	public Skill() {
		// TODO Auto-generated constructor stub
	}

	public Skill(String name, double level, String note) {
		super();
		this.name = name;
		this.level = level;
		this.note = note;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
