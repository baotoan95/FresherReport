package com.fresher.report.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "site_classes")
public class SiteClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String site;
	private String name;
	private String courseCode;
	private String attendeeType;
	private String subjectType;
	private String subSubjectType;
	private String formatType;
	private String scope;
	private String deliveryType;
	private String supplien;
	private Date plannedStartDate;
	private Date plannedEndDate;
	private int plannedLearningTime;
	private int planCountStudent;
	private float plannedExpense;
	private String projectCode;
	private Date actualStartDate;
	private int actualLearningTime;
	private int currentCountStudent;
	private float actualExpense;
	private int numberofEnrolledTrainee;
	private int numberofGraduates;
	private int trainingFeedback;
	private int trainingFeedbackContent;
	private int trainingFeedbackTeacher;
	private int trainingFeedbackOrganization;
	private String updateBy;
	private Date updateDate;
	private String note;
	private Date startDate;
	private Date endDate;
	private int learningTime;
	private int numberOfTrainee;
	private int expense;
	/*
	 * Status:
	 * In progress
	 * Done
	 * Planing
	 * */
	private String status;
	private int startYear;
	private int startMonth;
	private int endYear;
	private int endMonth;
	@DBRef// reference to another collection
	private Trainer headTeacher;
	@DBRef
	private Group group;
	private List<Trainee> trainees;
	private List<ClassIssue> listIssue;
	
	public SiteClass() {
		listIssue = new ArrayList<>();
	}

	public SiteClass(String site, String name, String courseCode, String attendeeType, String subjectType,
			String subSubjectType, String formatType, String scope, String deliveryType, String supplien,
			Date plannedStartDate, Date plannedEndDate, int plannedLearningTime, int planCountStudent,
			float plannedExpense, String projectCode, Date actualStartDate, int actualLearningTime,
			int currentCountStudent, float actualExpense, int numberofEnrolledTrainee, int numberofGraduates,
			int trainingFeedback, int trainingFeedbackContent, int trainingFeedbackTeacher,
			int trainingFeedbackOrganization, String updateBy, Date updateDate, String note, Date startDate,
			Date endDate, int learningTime, int numberOfTrainee, int expense, String status, int startYear,
			int startMonth, int endYear, int endMonth, Trainer headTeacher, Group group, List<Trainee> trainees,
			List<ClassIssue> listIssue) {
		this.site = site;
		this.name = name;
		this.courseCode = courseCode;
		this.attendeeType = attendeeType;
		this.subjectType = subjectType;
		this.subSubjectType = subSubjectType;
		this.formatType = formatType;
		this.scope = scope;
		this.deliveryType = deliveryType;
		this.supplien = supplien;
		this.plannedStartDate = plannedStartDate;
		this.plannedEndDate = plannedEndDate;
		this.plannedLearningTime = plannedLearningTime;
		this.planCountStudent = planCountStudent;
		this.plannedExpense = plannedExpense;
		this.projectCode = projectCode;
		this.actualStartDate = actualStartDate;
		this.actualLearningTime = actualLearningTime;
		this.currentCountStudent = currentCountStudent;
		this.actualExpense = actualExpense;
		this.numberofEnrolledTrainee = numberofEnrolledTrainee;
		this.numberofGraduates = numberofGraduates;
		this.trainingFeedback = trainingFeedback;
		this.trainingFeedbackContent = trainingFeedbackContent;
		this.trainingFeedbackTeacher = trainingFeedbackTeacher;
		this.trainingFeedbackOrganization = trainingFeedbackOrganization;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.note = note;
		this.startDate = startDate;
		this.endDate = endDate;
		this.learningTime = learningTime;
		this.numberOfTrainee = numberOfTrainee;
		this.expense = expense;
		this.status = status;
		this.startYear = startYear;
		this.startMonth = startMonth;
		this.endYear = endYear;
		this.endMonth = endMonth;
		this.headTeacher = headTeacher;
		this.group = group;
		this.trainees = trainees;
		this.listIssue = listIssue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getAttendeeType() {
		return attendeeType;
	}

	public void setAttendeeType(String attendeeType) {
		this.attendeeType = attendeeType;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public String getSubSubjectType() {
		return subSubjectType;
	}

	public void setSubSubjectType(String subSubjectType) {
		this.subSubjectType = subSubjectType;
	}

	public String getFormatType() {
		return formatType;
	}

	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getSupplien() {
		return supplien;
	}

	public void setSupplien(String supplien) {
		this.supplien = supplien;
	}

	public Date getPlannedStartDate() {
		return plannedStartDate;
	}

	public void setPlannedStartDate(Date plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}

	public Date getPlannedEndDate() {
		return plannedEndDate;
	}

	public void setPlannedEndDate(Date plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}

	public int getPlannedLearningTime() {
		return plannedLearningTime;
	}

	public void setPlannedLearningTime(int plannedLearningTime) {
		this.plannedLearningTime = plannedLearningTime;
	}

	public int getPlanCountStudent() {
		return planCountStudent;
	}

	public void setPlanCountStudent(int planCountStudent) {
		this.planCountStudent = planCountStudent;
	}

	public float getPlannedExpense() {
		return plannedExpense;
	}

	public void setPlannedExpense(float plannedExpense) {
		this.plannedExpense = plannedExpense;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public int getActualLearningTime() {
		return actualLearningTime;
	}

	public void setActualLearningTime(int actualLearningTime) {
		this.actualLearningTime = actualLearningTime;
	}

	public int getCurrentCountStudent() {
		return currentCountStudent;
	}

	public void setCurrentCountStudent(int currentCountStudent) {
		this.currentCountStudent = currentCountStudent;
	}

	public float getActualExpense() {
		return actualExpense;
	}

	public void setActualExpense(float actualExpense) {
		this.actualExpense = actualExpense;
	}

	public int getNumberofEnrolledTrainee() {
		return numberofEnrolledTrainee;
	}

	public void setNumberofEnrolledTrainee(int numberofEnrolledTrainee) {
		this.numberofEnrolledTrainee = numberofEnrolledTrainee;
	}

	public int getNumberofGraduates() {
		return numberofGraduates;
	}

	public void setNumberofGraduates(int numberofGraduates) {
		this.numberofGraduates = numberofGraduates;
	}

	public int getTrainingFeedback() {
		return trainingFeedback;
	}

	public void setTrainingFeedback(int trainingFeedback) {
		this.trainingFeedback = trainingFeedback;
	}

	public int getTrainingFeedbackContent() {
		return trainingFeedbackContent;
	}

	public void setTrainingFeedbackContent(int trainingFeedbackContent) {
		this.trainingFeedbackContent = trainingFeedbackContent;
	}

	public int getTrainingFeedbackTeacher() {
		return trainingFeedbackTeacher;
	}

	public void setTrainingFeedbackTeacher(int trainingFeedbackTeacher) {
		this.trainingFeedbackTeacher = trainingFeedbackTeacher;
	}

	public int getTrainingFeedbackOrganization() {
		return trainingFeedbackOrganization;
	}

	public void setTrainingFeedbackOrganization(int trainingFeedbackOrganization) {
		this.trainingFeedbackOrganization = trainingFeedbackOrganization;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getLearningTime() {
		return learningTime;
	}

	public void setLearningTime(int learningTime) {
		this.learningTime = learningTime;
	}

	public int getNumberOfTrainee() {
		return numberOfTrainee;
	}

	public void setNumberOfTrainee(int numberOfTrainee) {
		this.numberOfTrainee = numberOfTrainee;
	}

	public int getExpense() {
		return expense;
	}

	public void setExpense(int expense) {
		this.expense = expense;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public int getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}

	public Trainer getHeadTeacher() {
		return headTeacher;
	}

	public void setHeadTeacher(Trainer headTeacher) {
		this.headTeacher = headTeacher;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<Trainee> getTrainees() {
		return trainees;
	}

	public void setTrainees(List<Trainee> trainees) {
		this.trainees = trainees;
	}

	public List<ClassIssue> getListIssue() {
		return listIssue;
	}

	public void setListIssue(List<ClassIssue> listIssue) {
		this.listIssue = listIssue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}