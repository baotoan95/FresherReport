package com.fresher.report.entities;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "waiting_statistic")
public class WaitingStatistic {
	@Id
	private String id;
	private Date date;
	private int value;
	private String type;
	@DBRef
	private Group group;

	public WaitingStatistic() {
		// TODO Auto-generated constructor stub
	}

	public WaitingStatistic(Date date, int value, String type, Group group) {
		super();
		this.date = date;
		this.value = value;
		this.group = group;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "WaitingStatistic [id=" + id + ", date=" + date + ", value=" + value + ", group=" + group + "]";
	}

}
