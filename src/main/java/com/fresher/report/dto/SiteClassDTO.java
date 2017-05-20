package com.fresher.report.dto;

import java.util.List;

import com.fresher.report.entities.SiteClass;
import com.fresher.report.entities.Trainee;

public class SiteClassDTO {
	private SiteClass siteClass;
	private long failed;
	private long passed;
	private long dropOut;
	private List<Trainee> trainees;

	public SiteClassDTO() {
	}

	public SiteClassDTO(SiteClass siteClass, long failed, long passed, long dropOut, List<Trainee> trainees) {
		super();
		this.siteClass = siteClass;
		this.failed = failed;
		this.passed = passed;
		this.dropOut = dropOut;
		this.trainees = trainees;
	}

	public SiteClass getSiteClass() {
		return siteClass;
	}

	public void setSiteClass(SiteClass siteClass) {
		this.siteClass = siteClass;
	}

	public long getFailed() {
		return failed;
	}

	public void setFailed(long failed) {
		this.failed = failed;
	}

	public long getPassed() {
		return passed;
	}

	public void setPassed(long passed) {
		this.passed = passed;
	}

	public long getDropOut() {
		return dropOut;
	}

	public void setDropOut(long dropOut) {
		this.dropOut = dropOut;
	}

	public List<Trainee> getTrainees() {
		return trainees;
	}

	public void setTrainees(List<Trainee> trainees) {
		this.trainees = trainees;
	}

}
