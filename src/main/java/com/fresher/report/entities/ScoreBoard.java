package com.fresher.report.entities;

import java.util.List;

public class ScoreBoard {
	private Subject subject;
	private List<Point> listPoint;

	public ScoreBoard() {
		// TODO Auto-generated constructor stub
	}

	public ScoreBoard(Subject subject, List<Point> listPoint) {
		super();
		this.subject = subject;
		this.listPoint = listPoint;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubjectId(Subject subject) {
		this.subject = subject;
	}

	public List<Point> getListPoint() {
		return listPoint;
	}

	public void setListPoint(List<Point> listPoint) {
		this.listPoint = listPoint;
	}

}
