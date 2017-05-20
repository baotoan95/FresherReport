package com.fresher.report.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "groups")
public class Group implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String name;
	private ClassStatistic classStatistic;
	private PersonStatistic personStatistic;
	private List<WaitingStatistic> classWaitingHistories;
	private List<WaitingStatistic> personWaitingHistories;

	public Group() {
		// TODO Auto-generated constructor stub
	}

	public Group(String id, String name, ClassStatistic classStatistic, PersonStatistic personStatistic,
			List<WaitingStatistic> classWaitingHistories, List<WaitingStatistic> personWaitingHistories) {
		super();
		this.id = id;
		this.name = name;
		this.classStatistic = classStatistic;
		this.personStatistic = personStatistic;
		this.classWaitingHistories = classWaitingHistories;
		this.personWaitingHistories = personWaitingHistories;
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

	public ClassStatistic getClassStatistic() {
		return classStatistic;
	}

	public void setClassStatistic(ClassStatistic classStatistic) {
		this.classStatistic = classStatistic;
	}

	public PersonStatistic getPersonStatistic() {
		return personStatistic;
	}

	public void setPersonStatistic(PersonStatistic personStatistic) {
		this.personStatistic = personStatistic;
	}

	public List<WaitingStatistic> getClassWaitingHistories() {
		return classWaitingHistories;
	}

	public void setClassWaitingHistories(List<WaitingStatistic> classWaitingHistories) {
		this.classWaitingHistories = classWaitingHistories;
	}

	public List<WaitingStatistic> getPersonWaitingHistories() {
		return personWaitingHistories;
	}

	public void setPersonWaitingHistories(List<WaitingStatistic> personWaitingHistories) {
		this.personWaitingHistories = personWaitingHistories;
	}

}
