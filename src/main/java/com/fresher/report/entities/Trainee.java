package com.fresher.report.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "trainees")
public class Trainee implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String imagePath;
    private String employeeId;
    private String nationalId;
    private String account;
    private String fullName;
    private String branch;
    private String parentDepartment;
    private String university;
    private String faculty;
    private String email;
    private String phone;
    private String facebook;
    private String universityGraduation;
    private Date fullTimeWorkingDate;
    /*
     * Status: Drop-out Passed Failed
     */
    private String status;
    @DateTimeFormat(style="dd/MM/yyyy")
    private Date StarDate;
    @DateTimeFormat(style="dd/MM/yyyy")
    private Date endDate;
    private int learningTime;
    private double finalGrade;
    private String completionLevel;
    private String certificateProvider;
    private String certificateGroup;
    private String certificateSubGroup;
    private String certificateName;
    private String certificateCode;
    private String rECInterviewDate;
    private String rECInterviewStatus;
    private String note;
    private String attendeeType;
    private int stars;

    private List<Point> listPoint;
    private List<Skill> listSkill;
    private List<Comment> listComment;
    private List<AccessHistory> listAccessHistory;
    private List<Issue> listIssue;
    @DBRef
    private SiteClass siteClass;
    private String rank;

    public Trainee() {
		this.listPoint = new ArrayList<>();
		this.listSkill = new ArrayList<>();
		this.listComment = new ArrayList<>();
		this.listAccessHistory = new ArrayList<>();
		this.imagePath = "";
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getImagePath() {
	return imagePath;
    }

    public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
    }

    public String getEmployeeId() {
	return employeeId;
    }

    public void setEmployeeId(String employeeId) {
	this.employeeId = employeeId;
    }

    public String getNationalId() {
	return nationalId;
    }

    public void setNationalId(String nationalId) {
	this.nationalId = nationalId;
    }

    public String getAccount() {
	return account;
    }

    public void setAccount(String account) {
	this.account = account;
    }

    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    public String getBranch() {
	return branch;
    }

    public void setBranch(String branch) {
	this.branch = branch;
    }

    public String getParentDepartment() {
	return parentDepartment;
    }

    public void setParentDepartment(String parentDepartment) {
	this.parentDepartment = parentDepartment;
    }

    public String getUniversity() {
	return university;
    }

    public void setUniversity(String university) {
	this.university = university;
    }

    public String getFaculty() {
	return faculty;
    }

    public void setFaculty(String faculty) {
	this.faculty = faculty;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getFacebook() {
	return facebook;
    }

    public void setFacebook(String facebook) {
	this.facebook = facebook;
    }

    public String getUniversityGraduation() {
	return universityGraduation;
    }

    public void setUniversityGraduation(String universityGraduation) {
	this.universityGraduation = universityGraduation;
    }

    public Date getFullTimeWorkingDate() {
	return fullTimeWorkingDate;
    }

    public void setFullTimeWorkingDate(Date fullTimeWorkingDate) {
	this.fullTimeWorkingDate = fullTimeWorkingDate;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public Date getStarDate() {
	return StarDate;
    }

    public void setStarDate(Date StarDate) {
	this.StarDate = StarDate;
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

    public double getFinalGrade() {
	return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
	this.finalGrade = finalGrade;
    }

    public String getCompletionLevel() {
	return completionLevel;
    }

    public void setCompletionLevel(String completionLevel) {
	this.completionLevel = completionLevel;
    }

    public String getCertificateProvider() {
	return certificateProvider;
    }

    public void setCertificateProvider(String certificateProvider) {
	this.certificateProvider = certificateProvider;
    }

    public String getCertificateGroup() {
	return certificateGroup;
    }

    public void setCertificateGroup(String certificateGroup) {
	this.certificateGroup = certificateGroup;
    }

    public String getCertificateSubGroup() {
	return certificateSubGroup;
    }

    public void setCertificateSubGroup(String certificateSubGroup) {
	this.certificateSubGroup = certificateSubGroup;
    }

    public String getCertificateName() {
	return certificateName;
    }

    public void setCertificateName(String certificateName) {
	this.certificateName = certificateName;
    }

    public String getCertificateCode() {
	return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
	this.certificateCode = certificateCode;
    }

    public String getrECInterviewDate() {
	return rECInterviewDate;
    }

    public void setrECInterviewDate(String rECInterviewDate) {
	this.rECInterviewDate = rECInterviewDate;
    }

    public String getrECInterviewStatus() {
	return rECInterviewStatus;
    }

    public void setrECInterviewStatus(String rECInterviewStatus) {
	this.rECInterviewStatus = rECInterviewStatus;
    }

    public String getNote() {
	return note;
    }

    public void setNote(String note) {
	this.note = note;
    }

    public List<Point> getListPoint() {
	return listPoint;
    }

    public void setListPoint(List<Point> listPoint) {
	this.listPoint = listPoint;
    }

    public List<Skill> getListSkill() {
	return listSkill;
    }

    public void setListSkill(List<Skill> listSkill) {
	this.listSkill = listSkill;
    }

    public List<Comment> getListComment() {
	return listComment;
    }

    public void setListComment(List<Comment> listComment) {
	this.listComment = listComment;
    }

    public List<AccessHistory> getListAccessHistory() {
	return listAccessHistory;
    }

    public void setListAccessHistory(List<AccessHistory> listAccessHistory) {
	this.listAccessHistory = listAccessHistory;
    }

    public List<Issue> getListIssue() {
	return listIssue;
    }

    public void setListIssue(List<Issue> listIssue) {
	this.listIssue = listIssue;
    }

    public SiteClass getSiteClass() {
	return siteClass;
    }

    public void setSiteClass(SiteClass siteClass) {
	this.siteClass = siteClass;
    }

    public String getRank() {
	return rank;
    }

    public void setRank(String rank) {
	this.rank = rank;
    }

    public String getAttendeeType() {
	return attendeeType;
    }

    public void setAttendeeType(String attendeeType) {
	this.attendeeType = attendeeType;
    }

    public int getStars() {
	return stars;
    }

    public void setStars(int stars) {
	this.stars = stars;
    }

}
